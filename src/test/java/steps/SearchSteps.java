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
        this.homePage = new HomePage(context);
        this.itemPage = new ItemPage(context);
    }

    public final TestContext context;
    private final HomePage homePage;
    private final ItemPage itemPage;

    @Given("user clicks on Search field")
    public void userClicksOnSearchField() {
        homePage.userClicksOnSearchField();
    }

    @When("user inputs <searching item> and clicks on Search icon")
    public void userInputsSearchingItemAndClicksOnSearchIcon() {
        homePage.userInputsSearchingItemAndClicksOnSearchIcon();
    }

    @Then("user can find and choose the searching item on the page")
    public void userCanFindAndChooseTheSearchingItemOnThePage() {
        homePage.userCanFindAndChooseTheSearchingItemOnThePage();
    }

    @Then("user goes to item cart and adds the item to the shopping bag")
    public void userGoesToItemCartAndAddsTheItemToTheShoppingBag() {
        homePage.userGoesToItemCartAndAddsTheItemToTheShoppingBag();
    }

    @Then("message contains text <message>")
    public void messageContainsTextMessage() {

        String messageText = itemPage.messageContainsTextMessage();
        assertEquals("Success: You have added " +
                (ConfigurationReader.get("searching_item")) + " to your shopping cart!\n×", messageText);
    }

    @Given("user goes to Health and Beauty category and chooses it")
    public void userGoesToHealthAndBeautyCategoryAndChoosesIt() {
        homePage.chooseByCategory();
    }

    @When("user found the searching item on the page and click the Add to Cart button")
    public void userFoundTheSearchingItemOnThePageAndAdd() {
        homePage.addToCartFromCategory();
    }
}
