package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import testcomponents.BaseTest;

public class CartTest extends BaseTest {

    @Test
    public void removeProductFromCart() {

        String productName = "Sauce Labs Bike Light";

        driver.get(properties.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(
                properties.getProperty("username"),
                properties.getProperty("password")
        );

        productsPage.waitForProductsToAppear();

        productsPage.addProductToCart(productName);

        cartPage.goToCart();

        Assert.assertTrue(
                cartPage.isProductInCart(productName),
                "Product was not added to the cart"
        );

        cartPage.removeProductFromCart(productName);

        Assert.assertFalse(
                cartPage.isProductInCart(productName),
                "Product was not removed from the cart"
        );
    }

    @Test
    public void checkoutWithoutCustomerDetailsTest() {

        String productName = "Sauce Labs Bike Light";

        driver.get(properties.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login(
                properties.getProperty("username"),
                properties.getProperty("password")
        );

        productsPage.waitForProductsToAppear();

        productsPage.addProductToCart(productName);

        cartPage.goToCart();

        checkoutPage.checkout();

        checkoutPage.continueCheckout();

        Assert.assertTrue(
                checkoutPage.getErrorMessage()
                        .contains("First Name is required"),
                "Expected checkout validation message was not displayed"
        );
    }

    @Test
    public void checkoutWithoutPostalCodeTest() {

        String productName = "Sauce Labs Bike Light";

        driver.get(properties.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login(
                properties.getProperty("username"),
                properties.getProperty("password")
        );

        productsPage.waitForProductsToAppear();

        productsPage.addProductToCart(productName);

        cartPage.goToCart();

        checkoutPage.checkout();

        checkoutPage.enterCustomerName(
                "John",
                "Smith"
        );

        checkoutPage.continueCheckout();

        Assert.assertTrue(
                checkoutPage.getErrorMessage()
                        .contains("Postal Code is required"),
                "Expected postal code validation message was not displayed"
        );
    }
}