package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ItemPage;
import utils.ConfigurationReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchSteps {
    public SearchSteps(TestContext context) {
        this.context = context;
    }

    public final TestContext context;

    @Given("user clicks on Search field")
    public void userClicksOnSearchField() {
        HomePage homePage = new HomePage(context);
        homePage.userClicksOnSearchField();
    }

    @When("user inputs <searching item> and clicks on Search icon")
    public void userInputsSearchingItemAndClicksOnSearchIcon() {
        HomePage homePage = new HomePage(context);
        homePage.userInputsSearchingItemAndClicksOnSearchIcon();
    }

    @Then("user can find and choose the searching item on the page")
    public void userCanFindAndChooseTheSearchingItemOnThePage() {
        HomePage homePage = new HomePage(context);
        homePage.userCanFindAndChooseTheSearchingItemOnThePage();
    }

    @Then("user goes to item cart and adds the item to the shopping bag")
    public void userGoesToItemCartAndAddsTheItemToTheShoppingBag() {
        HomePage homePage = new HomePage(context);
        homePage.userGoesToItemCartAndAddsTheItemToTheShoppingBag();
    }

    @Then("message contains text <message>")
    public void messageContainsTextMessage() {
        ItemPage itemPage = new ItemPage(context);
        String messageText = itemPage.messageContainsTextMessage();
        assertEquals("Success: You have added " +
                (ConfigurationReader.get("searching_item")) + " to your shopping cart!\n×", messageText);
    }

    @Given("user goes to Health and Beauty category and chooses it")
    public void userGoesToHealthAndBeautyCategoryAndChoosesIt() {
        HomePage homePage = new HomePage(context);
        homePage.chooseByCategory();
    }

    @When("user found the searching item on the page and click the Add to Cart button")
    public void userFoundTheSearchingItemOnThePageAndAdd() {
        HomePage homePage = new HomePage(context);
        homePage.addToCartFromCategory();
    }

}
