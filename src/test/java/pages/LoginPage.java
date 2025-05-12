package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigurationReader;

public class LoginPage extends BasePage {
    public LoginPage(TestContext context) {
        super(context);
    }

    @FindBy(css = "[onclick*='ptlogin.loginAction']")
    public WebElement logInButton;

    @FindBy(xpath = "//*[@class='alert alert-danger']")
    public WebElement alertMessageWarning;

    @FindBy(css = "input[id='input-email']")
    public WebElement inputLogin;

    @FindBy(css = "input[id='input-password']")
    public WebElement inputPassword;

    @FindBy(css = "div[class='alert alert-success']")
    public WebElement alertSuccess;

    @FindBy(css = "[class='forgotten']")
    public WebElement forgottenPasswordLink;

    @FindBy(css = "input[value= Continue]")
    public WebElement continueButton;

    @FindBy(css = "div[class='alert alert-success alert-dismissible']")
    public WebElement emailSentConfirmMessage;

    @FindBy(css = "div[class='alert alert-danger alert-dismissible']")
    public WebElement warningMessage;


    public void clickLogInButton() {
        context.wait.until(ExpectedConditions.visibilityOf(logInButton)).click();
    }

    public String checkAlertMessage() {
        return context.wait.until(ExpectedConditions.visibilityOf(alertMessageWarning)).getText();
    }

    public void loginWithValidParameters(String login, String password) {

        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(login);
        inputPassword.sendKeys(password);
        clickLogInButton();
        context.wait.until(ExpectedConditions.visibilityOf(alertSuccess));
    }

    public void loginWithInvalidParameters(String login, String password) {

        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(login);
        inputPassword.sendKeys(password);
        clickLogInButton();
        context.wait.until(ExpectedConditions.visibilityOf(alertMessageWarning));

    }

    public void clickForgottenPasswordLink() {
        context.wait.until(ExpectedConditions.visibilityOf(forgottenPasswordLink)).click();
    }

    public void sendEmailToResetPassword() {
        context.wait.until(ExpectedConditions.visibilityOf(inputLogin)).sendKeys(ConfigurationReader.get("userName"));
        continueButton.click();
        context.wait.until(ExpectedConditions.visibilityOf
                (emailSentConfirmMessage));
    }

    public void sendInvalidEmailToResetPassword() {

        inputLogin.sendKeys(ConfigurationReader.get("invalidEmail"));
        continueButton.click();
        context.wait.until(ExpectedConditions.visibilityOf(warningMessage));
    }
}
