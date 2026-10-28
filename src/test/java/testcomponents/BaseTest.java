package testcomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties properties;

    public void loadProperties() throws IOException {

        String path = System.getProperty("user.dir")
                + "/src/test/resources/GlobalData.properties";

        properties = new Properties();

        try (FileInputStream file = new FileInputStream(path)) {
            properties.load(file);
        }
    }

    public WebDriver initializeDriver() {

        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else {

            throw new RuntimeException(
                    "Browser not supported: " + browserName
            );
        }

        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() throws IOException {

        loadProperties();
        driver = initializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}