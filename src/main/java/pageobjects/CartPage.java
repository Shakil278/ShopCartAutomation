
package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import reusable.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {

    private final By cartButton =
            By.cssSelector("[data-test='shopping-cart-link']");

    private final By cartProductsNames =
            By.cssSelector(".cart_item_label a");

    private final By cartItems =
            By.cssSelector(".cart_item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToCart() {
        clickElement(cartButton);
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> cartProducts =
                driver.findElements(cartProductsNames);

        return cartProducts.stream()
                .anyMatch(product ->
                        product.getText().trim()
                                .equalsIgnoreCase(productName.trim()));
    }

    public void removeProductFromCart(String productName) {
        driver.findElements(cartItems)
                .stream()
                .filter(item ->
                        item.findElement(
                                        By.cssSelector(".inventory_item_name"))
                                .getText()
                                .trim()
                                .equalsIgnoreCase(productName.trim()))
                .findFirst()
                .ifPresent(item ->
                        item.findElement(
                                By.cssSelector("button[data-test^='remove']")
                        ).click());
    }
}
