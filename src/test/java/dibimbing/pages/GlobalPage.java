package dibimbing.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GlobalPage extends BasePage {
  public GlobalPage(AndroidDriver driver) {
    super(driver);
  }

  @AndroidFindBy(accessibility = "View menu")
  private WebElement viewMenu;

  @AndroidFindBy(accessibility = "Login Menu Item")
  private WebElement loginMenu;

  public void clickHamburgerMenu() {
    viewMenu.click();
  }

  public void clickLoginMenu() {
    loginMenu.click();
  }
}
