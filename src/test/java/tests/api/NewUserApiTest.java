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


    @Test
    public void testMethod() {

        System.out.println("Step 1: New User");
        newUserApi();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Create new user");

        System.out.println("Step 2: Login to obtain token");
        generateTokenLoginUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Login as a new user to obtain the auth token");

        System.out.println("Step 3: Retrieve user details");
        retrieveUserDetails();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Retrieve user details");

        System.out.println("Step 4: Delete specific user as User");
        deleteUserAsUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete user with regular role (expect failure)");

        System.out.println("Step 5: Delete user with Admin role");
        // deleteAsAdmin

        System.out.println("Step 6: Check that created user was deleted successfully");
        // check again a GET user id with amdin and see that the user no longer exists
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

    public void retrieveUserDetails() {
        userActions.retrieveUserDetails(token, userId);
    }

    public void deleteUserAsUser() {
        userActions.deleteUserAsUser(token, userId);
    }

    // deleteAsAdmin - success

    // GET user id - as admin sa vedem ca chiar l-a sters
    // pot folosi aceeasi metoda cu if - 204;  else - 404


}
