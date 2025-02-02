package com.nbyula.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nbyula.utils.DriverManager;
import com.nbyula.utils.ExtentReportManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseTest {
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        log.info("Initializing WebDriver...");
        DriverManager.getDriver();

        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();

        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test Failed: " + result.getThrowable());

            // Capture Screenshot
            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + result.getName() + ".png";
            ExtentReportManager.captureScreenshot(driver, result.getName());

            try {
                test.fail("Test Failed: " + result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                test.fail("Failed to attach screenshot.");
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test passed successfully.");
        } else {
            test.log(Status.SKIP, "Test skipped.");
        }

        DriverManager.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}
