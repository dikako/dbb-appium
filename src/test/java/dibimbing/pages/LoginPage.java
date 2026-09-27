package dibimbing.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
  public LoginPage(AndroidDriver driver) {
    super(driver);
  }

  @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
  private WebElement usernameField;

  @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
  private WebElement passwordField;

  @AndroidFindBy(accessibility = "Tap to login with given credentials")
  private WebElement loginButton;

  @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameErrorTV")
  private WebElement alert;

  public void inputUsername(String username) {
    usernameField.sendKeys(username);
  }

  public void inputPassword(String password) {
    passwordField.sendKeys(password);
  }

  public void clickLoginButton() {
    loginButton.click();
  }

  public String getAlertText() {
    return alert.getText();
  }
}
