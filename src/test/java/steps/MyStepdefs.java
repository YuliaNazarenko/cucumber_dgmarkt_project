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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStepdefs {
    TestContext context;
    Scenario scenario;

    @Before
    public void beforeEach(Scenario scenario) {
        context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver,
                Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.driver.get(ConfigurationReader.get("url") + "startPageLogin");
        StartPage startPage = new StartPage(context);
        startPage.startPageLogin();
        context.js = (JavascriptExecutor) context.driver;

        this.scenario = scenario;
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) context.driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
        if (context.driver != null) {
            context.driver.quit();
        }
    }

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

    @Then("warning massage contains text <alertMassageWarningText>")
    public void warningMassageContainsTextAlertMassage() {
        LoginPage loginPage = new LoginPage(context);
        String alertMassageWarningText = loginPage.checkAlertMassage();
        assertTrue(alertMassageWarningText.equals("Warning: No match for E-Mail Address and/or Password.\n×")
                || alertMassageWarningText.equals("Warning: Your account has exceeded allowed number of login attempts. " +
                "Please try again in 1 hour.\n×"));
    }
}
