package hooks;

import core.logging.LoggerUtilityUi;
import core.reporting.ExtentUtilityUi;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Hooks extends ShareData {
    public String testName;

    @BeforeSuite
    public void initiateTestReport() {
        ExtentUtilityUi.initiateReport();
    }

    @BeforeMethod
    public void prepareEnvironment() {
        testName = this.getClass().getSimpleName();
        LoggerUtilityUi.startTestCase(testName);
        ExtentUtilityUi.createTest(testName);
        prepareBrowser();
    }

    @AfterMethod
    public void clearEnvironment(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String errorMessage = result.getThrowable().getMessage();
            LoggerUtilityUi.errorLog(result.getThrowable().getMessage());
            ExtentUtilityUi.attachLog(testName, errorMessage, getDriver());
        }
        clearBrowser();
        LoggerUtilityUi.endTestCase(testName);
        ExtentUtilityUi.finishTest(testName);
    }

    @AfterSuite
    public void finalizeLogFiles() {
        LoggerUtilityUi.mergeLogFilesIntoOne();
        ExtentUtilityUi.generateReport();
    }
}
