package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reusable.AbstractComponent;

public class LoginPage extends AbstractComponent {

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public void login(String usernameValue, String passwordValue) {
        driver.findElement(username).sendKeys(usernameValue);
        driver.findElement(password).sendKeys(passwordValue);
        clickElement(loginButton);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
