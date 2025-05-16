package steps;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    public LoginSteps(TestContext context) {
        this.context = context;
        this.loginPage = new LoginPage(context);
        this.homePage = new HomePage(context);
    }

    public final TestContext context;
    private final LoginPage loginPage;
    private final HomePage homePage;


    @Given("user opens login page")
    public void userOpensLoginPage() {
        homePage.openLoginForm();
    }

    @When("user click on login button")
    public void userClickOnLoginButton() {
        loginPage.clickLogInButton();
    }

    @Then("message contains text <Warning message>")
    public void warningMessageContainsTextAlertMessage() {

        String alertMessageWarningText = loginPage.checkAlertMessage();
        assertTrue(alertMessageWarningText.equals("Warning: No match for E-Mail Address and/or Password.\n×")
                || alertMessageWarningText.equals("Warning: Your account has exceeded allowed number of login attempts. " +
                "Please try again in 1 hour.\n×"));
    }

    @When("user enters login {string} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {

        loginPage.loginWithValidParameters(login, password);
    }

    @Then("message contains text {string}")
    public void messageContainsText(String successMessage) {

        String alertText = loginPage.alertSuccess.getText();
        assertEquals(successMessage, alertText);
    }

    @When("user click Forgotten Password link")
    public void userClickForgottenPasswordLink() {

        loginPage.clickForgottenPasswordLink();
    }

    @Then("user enters valid email and confirms")
    public void userEntersEmailAndConfirms() {

        loginPage.sendEmailToResetPassword();

    }

    @Then("alert message contains text <message>")
    public void userMessageContainsTextMessage() {

        String messageText = loginPage.emailSentConfirmMessage.getText();
        assertEquals("An email with a confirmation link has been sent your email address.",
                messageText);
    }


    @Then("user enters invalid email and confirms")
    public void userEntersInvalidEmailAndConfirms() {

        loginPage.sendInvalidEmailToResetPassword();
    }

    @Then("warning message contains text <message>")
    public void warningMessageContainsTextMessage() {

        String messageText = loginPage.warningMessage.getText();
        assertEquals("Warning: The E-Mail Address was not found in our records, please try again!",
                messageText);
    }

    @When("user enters invalid login {string} or invalid password {string}")
    public void userEntersInvalidData(String login, String password) {

        loginPage.loginWithInvalidParameters(login, password);
    }

    @Then("alert message contains text {string} or {string}")
    public void alertMessageContainsTextWarningMessage(String warningMessage, String alternativeMessage) {

        String alertText = loginPage.alertMessageWarning.getText();
        assertTrue(alertText.equals(warningMessage) || alertText.equals(alternativeMessage));
    }
}

