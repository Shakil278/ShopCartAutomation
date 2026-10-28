package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reusable.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

    private final By confirmationMessage =
            By.cssSelector("[data-test='complete-header']");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {

        waitForElementToAppear(confirmationMessage);

        return driver.findElement(confirmationMessage).getText();
    }
}