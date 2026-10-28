package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reusable.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    private final By checkoutButton = By.id("checkout");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.xpath("(//input[@id='continue'])");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {

        super(driver);
    }

    public void checkout() {

        clickElement(checkoutButton);
    }

    public void customerInfo(String firstNameValue, String lastNameValue, String postalCodeValue) {
        driver.findElement(firstName).sendKeys(firstNameValue);
        driver.findElement(lastName).sendKeys(lastNameValue);
        driver.findElement(postalCode).sendKeys(postalCodeValue);

    }

    public void continueCheckout() {

        clickElement(continueButton);
    }

    public void finishOrder() {

        clickElement(finishButton);
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return driver.findElement(errorMessage).getText();
    }

    public void enterCustomerName(String firstNameValue, String lastNameValue) {
        driver.findElement(firstName).sendKeys(firstNameValue);
        driver.findElement(lastName).sendKeys(lastNameValue);
    }
}
