package pages;

import context.TestContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(TestContext context) {
        super(context);
    }
    @FindBy(css = "a[data-toggle='dropdown']")
    public WebElement myAccount;

    @FindBy(id = "pt-login-link")
    public WebElement logIn;

    public void openLoginForm() {
        context.wait.until(ExpectedConditions.visibilityOf(myAccount)).click();
        context.wait.until(ExpectedConditions.visibilityOf(logIn)).click();
    }

}
