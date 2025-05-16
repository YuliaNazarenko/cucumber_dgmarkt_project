package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HomePage;
import utils.ConfigurationReader;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeSteps {
    public HomeSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context);
    }

    public final TestContext context;
    private final HomePage homePage;

    @Given("user is on home page")
    public void userIsOnHomePage() {
    }

    @When("user selects a target currency from drop down list")
    public void userSelectsATargetCurrencyFromDropDownList() throws InterruptedException {
        homePage.changeCurrency();
        sleep(500);
    }

    @Then("the currency is as selected")
    public void theCurrencyIsAsSelected() {
        assertEquals(ConfigurationReader.get("TestChangeCurrency").substring(0, 1),
                context.wait.until(ExpectedConditions.visibilityOf(homePage.currentCurrency)).getText());
    }
}
