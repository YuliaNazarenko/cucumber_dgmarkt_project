package pages;

import context.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(TestContext context) {
        super(context);
    }

    //Top of the page elements

    @FindBy(css = "[id='form-currency']")
    public WebElement currency;

    @FindBy(xpath = "//*[@class='currency-select btn btn-link btn-block']")
    public List<WebElement> listOfCurrencies;

    @FindBy(xpath = "//li[@class='currency']//button/span[1]")
    public WebElement currentCurrency;


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

    //Category

    @FindBy(css = "a[href='Camera']")
    public WebElement menuCategory;

    @FindBy(css = "div.mega-menu-container a[href*='path=62']")
    public WebElement healthAndBeauty;


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

    public void userCanFindAndChooseTheSearchingItemOnThePage() {
        String targetItem = (ConfigurationReader.get("searching_item"));

        for (WebElement item : nickyClarkeItems) {
            String itemText = item.getText();
            if (itemText.equalsIgnoreCase(targetItem)) {
                item.click();
                break;
            }
        }
    }

    public void userGoesToItemCartAndAddsTheItemToTheShoppingBag() {
        context.wait.until(ExpectedConditions.visibilityOf
                (new ItemPage(context).addToCartButton)).click();
    }

    public void chooseByCategory() {
        Actions actions = new Actions(context.driver);
        actions.moveToElement(menuCategory).perform();
        context.wait.until(ExpectedConditions.visibilityOf(healthAndBeauty)).click();
    }

    public void addToCartFromCategory() {
        Actions actions = new Actions(context.driver);
        WebElement addToCartButton = findAddToCartButtonByItem(ConfigurationReader.get("searching_item"));
        actions.moveToElement(addToCartButton).perform();
        addToCartButton.click();

        context.wait.until(ExpectedConditions.visibilityOf(new ItemPage(context).alertMessage));
    }

    //Find the item's LOCATOR in category method
    public WebElement findAddToCartButtonByItem(String searchingItem) {

        WebElement button;
        ((JavascriptExecutor) context.driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                button = context.wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//*[contains(text(), '" + searchingItem + "')]" +
                                "/ancestor::div[contains(@class, 'caption')]//button"))));

        return button;
    }

    public void changeCurrency() {
        context.wait.until(ExpectedConditions.visibilityOf(currency)).click();
        String targetCurrency = ConfigurationReader.get("TestChangeCurrency");

        for (WebElement currency : listOfCurrencies) {
            String currencyText = currency.getText();
            if (currencyText.equalsIgnoreCase(targetCurrency)) {
                currency.click();
                break;
            }
        }
    }
}
