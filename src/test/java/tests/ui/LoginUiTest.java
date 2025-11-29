package tests.ui;

import core.reporting.ExtentUtilityUi;
import core.reporting.ReportStep;
import core.utils.property.PropertyUtility;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.object.data.ui.LoginObject;
import ui.pages.AdminHomePage;
import ui.pages.LoginPage;
import hooks.UiTestsHook;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class LoginUiTest extends UiTestsHook {

    public LoginObject loginObject;
    public PropertyUtility propertyUtility;
    public LoginPage loginPage;
    public AdminHomePage homePage;

    @BeforeMethod
    public void setupPageObjects() {
        loginPage = new LoginPage(getDriver());
        propertyUtility = new PropertyUtility("request-data/admin-credentials-data");
        loginObject = new LoginObject(propertyUtility.getAllData());
    }


    @Test
    public void emptyLoginTest() {
        loginPage.openLoginForm();
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "The user clicked Sign In button.");

        loginPage.clickLogin();
        assertEquals("Email is required", loginPage.getEmailError());
        assertEquals("Password is required", loginPage.getPasswordError());
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "Login attempt with no email and password values.");

    }

    @Test
    public void invalidLoginTest() {
        loginPage.openLoginForm();
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "The user clicked Sign In button.");

        loginPage.fillInvalidCredentials("admin@mail", "pswd");
        loginPage.clickLogin();
        assertEquals("Invalid email or password", loginPage.getLoginErrorMessage());
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "Login attempt with invalid email and password.");
    }

    @Test
    public void validLoginTest() {
        loginPage.openLoginForm();
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "The user clicked Sign In button.");

        loginPage.fillValidCredentials(loginObject);
        loginPage.clickLogin();

        homePage = new AdminHomePage(getDriver());
        assertTrue(homePage.checkPageTitle("Sales over the years"));
        ExtentUtilityUi.attachLog(ReportStep.PASS_STEP, "Login successful and AdminHomePage loaded.");
    }

}
