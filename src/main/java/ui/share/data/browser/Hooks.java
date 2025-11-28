/*
package ui.share.data.browser;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Hooks extends ShareData {
    public String testName;

    @BeforeSuite
    public void initiateTestReport() {
        ExtentUtility.initiateReport();
    }

    @BeforeMethod
    public void prepareEnvironment() {
        testName = this.getClass().getSimpleName();
        LoggerUtility.startTestCase(testName);
        ExtentUtility.createTest(testName);
        prepareBrowser();
    }

    @AfterMethod
    public void clearEnvironment(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String errorMessage = result.getThrowable().getMessage();
            LoggerUtility.errorLog(result.getThrowable().getMessage());
            ExtentUtility.attachLog(testName, errorMessage, getDriver());
        }
        clearBrowser();
        LoggerUtility.endTestCase(testName);
        ExtentUtility.finishTest(testName);
    }

    @AfterSuite
    public void finalizeLogFiles() {
        LoggerUtility.mergeLogFilesIntoOne();
        ExtentUtility.generateReport();
    }
}
*/
