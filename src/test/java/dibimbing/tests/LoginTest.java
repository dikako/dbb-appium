package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.GlobalPage;
import dibimbing.pages.LoginPage;
import dibimbing.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
  public void testSuccessLogin() {
    GlobalPage globalPage = new GlobalPage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    ProductPage productPage = new ProductPage(DriverManager.getDriver());

    // Steps
    globalPage.clickHamburgerMenu();
    globalPage.clickLoginMenu();

    loginPage.inputUsername("bod@example.com");
    loginPage.inputPassword("10203040");
    loginPage.clickLoginButton();

    String productPageTitle = productPage.getTitle();
    Assert.assertEquals(productPageTitle, "Products");
  }

  @Test
  public void testFailedLogin() {
    GlobalPage globalPage = new GlobalPage(DriverManager.getDriver());
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());

    // Steps
    globalPage.clickHamburgerMenu();
    globalPage.clickLoginMenu();

    loginPage.inputUsername("");
    loginPage.inputPassword("");
    loginPage.clickLoginButton();

    String alertText = loginPage.getAlertText();
    Assert.assertEquals(alertText, "Username is required");
  }
}
