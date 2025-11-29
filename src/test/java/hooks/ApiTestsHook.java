package hooks;

import core.logging.LoggerUtilityApi;
import core.reporting.ExtentUtilityApi;
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
        ExtentUtilityApi.initiateReport();
    }

    @BeforeMethod
    public void prepareTest() {
        testName = this.getClass().getSimpleName();
        LoggerUtilityApi.startTestCase(testName);
        ExtentUtilityApi.startTest(testName);
    }

    @AfterClass
    public void clearTest() {
        LoggerUtilityApi.finishTestCase(testName);
        ExtentUtilityApi.finishTest(testName);
    }

    @AfterSuite
    public void clearSuite() {
        ExtentUtilityApi.generateReport();
    }
}
