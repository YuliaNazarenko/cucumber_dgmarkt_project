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
import pages.LoginPage;
import pages.StartPage;
import utils.ConfigurationReader;
import utils.DriverFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    TestContext context;
    Scenario scenario;


    @Given("user opens login page")
    public void userOpensLoginPage() {
        HomePage homePage = new HomePage(context);
        homePage.openLoginForm();
    }

    @When("user click on login button")
    public void userClickOnLoginButton() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.clickLogInButton();
    }

    @Then("message contains text <Warning message>")
    public void warningMessageContainsTextAlertMessage() {
        LoginPage loginPage = new LoginPage(context);
        String alertMessageWarningText = loginPage.checkAlertMessage();
        assertTrue(alertMessageWarningText.equals("Warning: No match for E-Mail Address and/or Password.\n×")
                || alertMessageWarningText.equals("Warning: Your account has exceeded allowed number of login attempts. " +
                "Please try again in 1 hour.\n×"));
    }

    @When("user enters login {string} and password {string}")
    public void userEntersLoginAndPassword(String login, String password) {
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginWithValidParameters(login, password);
    }

    @Then("message contains text {string}")
    public void messageContainsText(String successMessage) {
        LoginPage loginPage = new LoginPage(context);
        String alertText = loginPage.alertSuccess.getText();

        assertEquals(successMessage, alertText);
    }

    @When("user click Forgotten Password link")
    public void userClickForgottenPasswordLink() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.clickForgottenPasswordLink();
    }

    @Then("user enters valid email and confirms")
    public void userEntersEmailAndConfirms() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.sendEmailToResetPassword();

    }

    @Then("alert message contains text <message>")
    public void userMessageContainsTextMessage() {
        LoginPage loginPage = new LoginPage(context);
        String messageText = loginPage.emailSentConfirmMessage.getText();
        assertEquals("An email with a confirmation link has been sent your email address.",
                messageText);
    }


    @Then("user enters invalid email and confirms")
    public void userEntersInvalidEmailAndConfirms() {
        LoginPage loginPage = new LoginPage(context);
        loginPage.sendInvalidEmailToResetPassword();
    }

    @Then("warning message contains text <message>")
    public void warningMessageContainsTextMessage() {
        LoginPage loginPage = new LoginPage(context);
        String messageText = loginPage.warningMessage.getText();
        assertEquals("Warning: The E-Mail Address was not found in our records, please try again!",
                messageText);
    }

    @When("user enters invalid login {string} or invalid password {string}")
    public void userEntersInvalidData(String login, String password) {
        LoginPage loginPage = new LoginPage(context);
        loginPage.loginWithInvalidParameters(login, password);
    }

    @Then("alert message contains text {string} or {string}")
    public void alertMessageContainsTextWarningMessage(String warningMessage, String alternativeMessage) {
        LoginPage loginPage = new LoginPage(context);
        String alertText = loginPage.alertMessageWarning.getText();

        assertTrue(alertText.equals(warningMessage) || alertText.equals(alternativeMessage));
    }
}

