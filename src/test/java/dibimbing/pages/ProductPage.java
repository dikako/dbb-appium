package dibimbing.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {
  public ProductPage(AndroidDriver driver) {
    super(driver);
  }

  @AndroidFindBy(accessibility = "title")
  private WebElement productTitle;

  public String getTitle() {
    return productTitle.getText();
  }
}
