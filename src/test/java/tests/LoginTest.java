package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import testcomponents.BaseTest;
import utilities.DataReader;
import utilities.LoginDataSet;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {

        driver.get(properties.getProperty("url"));

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login(username, password);

        productsPage.waitForProductsToAppear();

        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Product is not displayed");

    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);

        driver.get(properties.getProperty("url"));

        loginPage.login(username, password);

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Expected login error message was not displayed");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {

        DataReader dataReader = new DataReader();
        LoginDataSet data = dataReader.getLoginData();

        return new Object[][]{
                {
                        data.getValidLogin().getUsername(),
                        data.getValidLogin().getPassword()
                }

        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() throws Exception {

        DataReader dataReader = new DataReader();
        LoginDataSet data = dataReader.getLoginData();

        return new Object[][]{
                {
                        data.getInvalidLogin().getUsername(),
                        data.getInvalidLogin().getPassword()
                }

        };
    }
}
