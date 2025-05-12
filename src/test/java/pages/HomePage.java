package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

import java.util.List;

public class HomePage extends BasePage{
    public HomePage(TestContext context) {
        super(context);
    }
    //Account elements----------------------------------------------

    @FindBy(css = "a[data-toggle='dropdown']")
    public WebElement myAccount;
    @FindBy(css = "a[id=pt-register-link]")
    public WebElement register;
    @FindBy(id = "pt-login-link")
    public WebElement logIn;

    //Search--------------------------------------------------------------------

    @FindBy(css = "div[class='dropdown-toggle search-button']")
    public WebElement searchOpen;

    @FindBy(css = "input[id = 'text-search']")
    public WebElement inputTextSearch;

    @FindBy(css = "button[id='btn-search-category']")
    public WebElement search;

    @FindBy(xpath = "//a[contains(text(), 'Nicky Clarke')]")
    public List<WebElement> nickyClarkeItems;


    public void openLoginForm() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        context.wait.until(ExpectedConditions.visibilityOf(logIn)).click();
    }

    public void userClicksOnSearchField() {
        context.wait.until(ExpectedConditions.visibilityOf(searchOpen)).click();
    }

    public void userInputsSearchingItemAndClicksOnSearchIcon() {
        context.wait.until(ExpectedConditions.visibilityOf(inputTextSearch)).
                sendKeys(ConfigurationReader.get("searching_item").substring(0, 12));
        search.click();
    }

    public void userCanFindAndChooseTheSearchingItemOnThePage () {
        String targetItem = (ConfigurationReader.get("searching_item"));

        for (WebElement item : nickyClarkeItems) {
            String itemText = item.getText();
            if (itemText.equalsIgnoreCase(targetItem)) {
                item.click();
                break;
            }
        }
    }

    public void userGoesToItemCartAndAddsTheItemToTheShoppingBag () {
        context.wait.until(ExpectedConditions.visibilityOf
                (new ItemPage(context).addToCartButton)).click();
    }



}
