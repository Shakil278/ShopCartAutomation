package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporter {

    private static ExtentReports extent;

    public static ExtentReports getReportObject() {

        if (extent == null) {

            String reportDirectory =
                    System.getProperty("user.dir") + "/reports";

            File directory = new File(reportDirectory);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String path = reportDirectory + "/index.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(path);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Shakil Mahmood");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}
