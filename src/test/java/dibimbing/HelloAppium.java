package dibimbing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class HelloAppium {
  private AndroidDriver driver;

  @BeforeClass
  public void setUp() {
    UiAutomator2Options options = new UiAutomator2Options()
      .setDeviceName("emulator-5554")
      .setApp(System.getProperty("user.dir") + "/apk/demo.apk")
      .setAppWaitActivity("*");

    try {
      URL appiumServerUrl = new URL("http://127.0.0.1:4723");
      driver = new AndroidDriver(appiumServerUrl, options);
      System.out.println("Driver session started!");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void helloAppium() throws InterruptedException {
    assert driver.getSessionId() != null;
    System.out.println("Session ID: " + driver.getSessionId());

    driver.findElement(AppiumBy.accessibilityId("View menu")).click();
    driver.findElement(AppiumBy.accessibilityId("Login Menu Item")).click();
    driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")).sendKeys("bod@example.com");
    driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET")).sendKeys("10203040");
    driver.findElement(AppiumBy.accessibilityId("Tap to login with given credentials")).click();

    // example after login -> click cart
    driver.findElement(AppiumBy.accessibilityId("Displays number of items in your cart")).click();
    Thread.sleep(10000); // for demo test only!!
  }

  @Test
  public void testWebview() throws InterruptedException {
    assert driver.getSessionId() != null;

    driver.findElement(AppiumBy.accessibilityId("View menu")).click();
    driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"WebView\")")).click();
    driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlET")).sendKeys("dikacore.dev");
    driver.findElement(AppiumBy.accessibilityId("Tap to view content of given url")).click();
    Thread.sleep(5000); // for demo test only!!


    Set<String> contexts = driver.getContextHandles();
    System.out.println("Context handles: " + contexts);

    for (String context : contexts) {
      if (context.contains("WEBVIEW")) {
        System.out.println("Context found: " + context);
        driver.context(context);
      }
    }

    System.out.println("Title of web view: " + driver.getTitle());
    Thread.sleep(5000); // for demo test only!!
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("Driver session quitted!");
    }
  }
}
