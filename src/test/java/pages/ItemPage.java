package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class ItemPage extends BasePage{
    public ItemPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "button[id='button-cart']")
    public WebElement addToCartButton;

    @FindBy(css = "button[title='Add to Wish List']")
    public WebElement addToWishListButton;

    @FindBy(css = "div[class=inner] h1")
    public WebElement titleOfItem;

    @FindBy(xpath = "//*[@class ='alert alert-fix alert-success alert-dismissible']")
    public WebElement alertMessage;



    public String addToCart() {
        context.wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    public String addToWishList() {

        context.driver.get(ConfigurationReader.get("item_url"));
        context.wait.until(ExpectedConditions.visibilityOf(addToWishListButton)).click();
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessage)).getText();
    }

    public String messageContainsTextMessage () {
        return context.wait.until(ExpectedConditions.visibilityOf
                (new ItemPage(context).alertMessage)).getText();
    }

}
