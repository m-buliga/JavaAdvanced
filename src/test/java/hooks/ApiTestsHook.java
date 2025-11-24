package hooks;

import core.logging.LoggerUtility;
import core.reporting.ExtentUtility;
import core.utils.property.PropertyUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class ApiTestsHook {
    private String testName;
    public PropertyUtility propertyUtility;

    @BeforeSuite
    public void prepareSuite() {
        ExtentUtility.initiateReport();
    }

    @BeforeMethod
    public void prepareTest() {
        testName = this.getClass().getSimpleName();
        LoggerUtility.startTestCase(testName);
        ExtentUtility.startTest(testName);
    }

    @AfterClass
    public void clearTest() {
        LoggerUtility.finishTestCase(testName);
        ExtentUtility.finishTest(testName);
    }

    @AfterSuite
    public void clearSuite() {
        ExtentUtility.generateReport();
    }
}
