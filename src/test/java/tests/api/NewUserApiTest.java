package tests.api;

import api.actions.UserActions;
import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseUserSuccess;
import core.reporting.ExtentUtility;
import core.reporting.ReportStep;
import core.utils.property.PropertyUtility;
import hooks.ApiTestsHook;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewUserApiTest extends ApiTestsHook {

    public RequestUser requestUserBody;
    public String token;
    public String userId;
    public UserActions userActions;
    public String adminToken;


    @Test
    public void testMethod() {

        System.out.println("Step 1: New User");
        newUserApi();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Create new user");

        System.out.println("Step 2: Login to obtain token");
        generateTokenLoginUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Login as a new user to obtain the auth token");

        System.out.println("Step 3: Retrieve user details as User");
        retrieveUserDetailsAsUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Retrieve user details");

        System.out.println("Step 4: Delete specific user as User");
        deleteUserAsUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete user with regular role (expect failure)");

        System.out.println("Step 5: Delete user with Admin role");
        getAdminToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get admin token");
        deleteUserAsAdmin();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete user with admin role");

        System.out.println("Step 6: Check that created user was deleted successfully by Admin user");
        retrieveUserDetailsAsAdmin();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Created user no longer exists");
    }

    public void newUserApi() {
        userActions = new UserActions();

        PropertyUtility propertyUtility = new PropertyUtility("request-data/new-user-test-data");
        requestUserBody = new RequestUser(propertyUtility.getAllData());

        ResponseUserSuccess responseUserSuccessBody = userActions.createNewUser(requestUserBody);
        userId = responseUserSuccessBody.getId();
    }

    public void generateTokenLoginUser() {
        ResponseLoginTokenSuccess responseLoginTokenSuccess = userActions.responseLoginTokenSuccess(requestUserBody);
        token = responseLoginTokenSuccess.getAccessToken();
    }

    public void retrieveUserDetailsAsUser() {
        userActions.retrieveUserDetails(token, userId);
    }

    public void deleteUserAsUser() {
        userActions.deleteUserAsUser(token, userId);
    }

    public void getAdminToken() {

        PropertyUtility propertyUtility = new PropertyUtility("request-data/admin-credentials-data");
        requestUserBody = new RequestUser(propertyUtility.getAllData());

        ResponseLoginTokenSuccess responseLoginTokenSuccess = userActions.responseLoginTokenSuccess(requestUserBody);
        adminToken = responseLoginTokenSuccess.getAccessToken();
    }


    public void deleteUserAsAdmin() {
        userActions.deleteUserAsAdmin(adminToken, userId);
    }

    public void retrieveUserDetailsAsAdmin() {
        userActions.retrieveUserDetails(adminToken, userId);
    }


}
