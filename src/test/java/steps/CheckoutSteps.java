package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutSteps {
    public CheckoutSteps(TestContext context) {
        this.context = context;
        this.checkoutPage = new CheckoutPage(context);
    }
    public final TestContext context;
    private final CheckoutPage checkoutPage;

    @Given("user has the added item in the shopping bag")
    public void userHasTheAddedItemInTheShoppingBag() {
        checkoutPage.addToCardByLink();
    }

    @When("user clicks on shopping bag item the checkout form opens")
    public void userClicksOnShoppingBagItemTheCheckoutFormOpens() {
        checkoutPage.goToCheckout();
    }

    @Then("user fills up the guest checkout form")
    public void userFillsUpTheGuestCheckoutForm() {
        checkoutPage.fillGuestCheckoutForm();
    }

    @Then("message contains text <title>")
    public void messageContainsTextYourOrderHasBeenPlaced() {
        assertEquals("Your order has been placed!",
                context.driver.getTitle());
    }
}
