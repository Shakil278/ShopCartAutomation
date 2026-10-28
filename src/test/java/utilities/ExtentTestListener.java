package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testcomponents.BaseTest;

import java.io.IOException;

public class ExtentTestListener implements ITestListener {

    private static final Logger logger =
            LogManager.getLogger(ExtentTestListener.class);

    private final ExtentReports extent =
            ExtentReporter.getReportObject();

    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);

        logger.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test passed");

        logger.info("Test passed: " + result.getMethod().getMethodName());

        test.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        logger.error(
                "Test failed: " + result.getMethod().getMethodName(),
                result.getThrowable()
        );

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            try {
                String screenshotPath =
                        ScreenshotUtil.captureScreenshot(
                                driver,
                                result.getMethod().getMethodName()
                        );

                test.get().addScreenCaptureFromPath(screenshotPath);

                logger.info("Screenshot captured: " + screenshotPath);

            } catch (IOException e) {
                logger.error("Failed to capture screenshot", e);
            }
        } else {
            logger.warn("Screenshot not captured because WebDriver is null");
        }

        test.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test skipped");

        logger.warn("Test skipped: " + result.getMethod().getMethodName());

        test.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        logger.info("Extent report generated");
    }
}
