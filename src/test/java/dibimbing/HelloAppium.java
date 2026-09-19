package dibimbing;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

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
  public void helloAppium() {
    assert driver.getSessionId() != null;
    System.out.println("Session ID: " + driver.getSessionId());
  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
      System.out.println("Driver session quitted!");
    }
  }
}
