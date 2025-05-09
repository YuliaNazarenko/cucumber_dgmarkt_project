package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(TestContext context) {
        super(context);
    }
    @FindBy(css = "[onclick*='ptlogin.loginAction']")
    public WebElement logInButton;

    @FindBy(xpath = "//*[@class='alert alert-danger']")
    public WebElement alertMassageWarning;


    public void clickLogInButton() {
        context.wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
    }

    public String checkAlertMassage() {
        return context.wait.until(ExpectedConditions.visibilityOf(alertMassageWarning)).getText();
    }
}
