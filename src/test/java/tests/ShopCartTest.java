package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import testcomponents.BaseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShopCartTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ShopCartTest.class);

    @Test
    public void submitOrderTest() {

        String productName = "Sauce Labs Bike Light";

        logger.info("Starting order test");

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        driver.get(properties.getProperty("url"));
        logger.info("Navigating to the order page");

        loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

        logger.info("User logged in successfully");

        productsPage.waitForProductsToAppear();

        productsPage.addProductToCart(productName);

        cartPage.goToCart();
        logger.info("Navigated to the cart");

        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found!");
        logger.info("Product is verified in the cart");

        checkoutPage.checkout();
        logger.info("Started checkout");

        checkoutPage.customerInfo("John", "Smith", "3028");
        logger.info("Customer info has been entered");

        checkoutPage.continueCheckout();
        logger.info("Continue checkout");

        checkoutPage.finishOrder();
        logger.info("Finished checkout");

        Assert.assertEquals(confirmationPage.getConfirmationMessage(), "Thank you for your order!");
        logger.info("Order has been verified");

        System.out.println("Order has been successfully placed!");

    }
}
