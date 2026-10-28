package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String testName
    ) throws IOException {

        String screenshotDirectory =
                System.getProperty("user.dir") + "/screenshots";

        File directory = new File(screenshotDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String destination = screenshotDirectory
                + "/" + testName + ".png";

        File target = new File(destination);

        Files.copy(
                source.toPath(),
                target.toPath(),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        return destination;
    }
}
