package steps;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ItemPage;
import pages.StartPage;
import utils.ConfigurationReader;
import utils.DriverFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchSteps {
    TestContext context;
    Scenario scenario;


    @Given("user clicks on Search field")
    public void userClicksOnSearchField () {
        HomePage homePage = new HomePage(context);
        homePage.userClicksOnSearchField();
    }

    @When("user inputs <searching item> and clicks on Search icon")
    public void userInputsSearchingItemAndClicksOnSearchIcon () {
        HomePage homePage = new HomePage(context);
        homePage.userInputsSearchingItemAndClicksOnSearchIcon();
    }

    @Then("user can find and choose the searching item on the page")
    public void userCanFindAndChooseTheSearchingItemOnThePage () {
        HomePage homePage = new HomePage(context);
        homePage.userCanFindAndChooseTheSearchingItemOnThePage();
    }

    @Then("user goes to item cart and adds the item to the shopping bag")
    public void userGoesToItemCartAndAddsTheItemToTheShoppingBag () {
        HomePage homePage = new HomePage(context);
        homePage.userGoesToItemCartAndAddsTheItemToTheShoppingBag();
    }

    @Then("message contains text <message>")
    public void messageContainsTextMessage () {
        ItemPage itemPage = new ItemPage(context);
        String messageText = itemPage.messageContainsTextMessage();
        assertEquals("Success: You have added " +
                (ConfigurationReader.get("searching_item")) + " to your shopping cart!\n×", messageText);
    }
}
