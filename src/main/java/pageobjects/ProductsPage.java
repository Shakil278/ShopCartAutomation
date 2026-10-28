package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reusable.AbstractComponent;

import java.util.List;

public class ProductsPage extends AbstractComponent {
    private final By products = By.cssSelector(("[data-test='inventory-item']"));

    public ProductsPage(WebDriver driver) {

        super(driver);
    }

    public WebElement getProduct(String productName) {
        List<WebElement> productList = driver.findElements(products);
        WebElement product = productList.stream()
                .filter(item ->
                        item.findElement(By.cssSelector(("[data-test='inventory-item-name']")))
                                .getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        return product;
    }

    public void addProductToCart(String productName) {
        WebElement product = getProduct(productName);
        product.findElement(By.cssSelector("button[data-test^='add-to-cart']")).click();
    }

    public boolean isProductsPageDisplayed() {
        return driver.findElement(products).isDisplayed();
    }

    public void waitForProductsToAppear() {

        waitForElementToAppear(products);
    }
}
