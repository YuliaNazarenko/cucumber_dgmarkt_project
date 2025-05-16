package steps;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.StartPage;
import utils.ConfigurationReader;
import utils.DriverFactory;

import java.time.Duration;

public class Hooks {
    public Hooks(TestContext context) {
        this.context = context;
    }

    private final TestContext context;
    Scenario scenario;

    @Before
    public void beforeEach(Scenario scenario) {
        //context = new TestContext();
        context.driver = DriverFactory.get();
        context.wait = new WebDriverWait(context.driver,
                Duration.ofSeconds(Long.parseLong(ConfigurationReader.get("timeout"))));
        context.actions = new Actions(context.driver);
        context.driver.get(ConfigurationReader.get("url"));
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
}
