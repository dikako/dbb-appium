package dibimbing;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
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
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      System.out.println("Driver session started!");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void helloAppium() throws InterruptedException {
    assert driver.getSessionId() != null;
    System.out.println("Session ID: " + driver.getSessionId());

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    WebElement elementViewMenu = wait.until(
      ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View menu"))
    );
    elementViewMenu.click();

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

  @Test
  public void clickByCoordinate() throws InterruptedException { // rare case
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);

    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
      PointerInput.Origin.viewport(), 1000, 193));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(tap));

    Thread.sleep(5000); // for demo test only!!
  }

  @Test
  public void swipe() throws InterruptedException {
    Dimension size = driver.manage().window().getSize();
    System.out.println("Size: " + size);

    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endY = (int) (size.getHeight() * 0.2);

    System.out.println("Start X: " + startX + " Start Y: " + startY + " End Y: " + endY);

    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);

    swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
      PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(Duration.ofMillis(2000),
      PointerInput.Origin.viewport(), startX, endY));
    swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(swipe));

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
