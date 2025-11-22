package tests.api;

import api.actions.ContactMessageActions;
import api.actions.UserActions;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseUserSuccess;
import core.reporting.ExtentUtility;
import core.reporting.ReportStep;
import core.utils.property.PropertyUtility;
import hooks.ApiTestsHook;
import org.testng.annotations.Test;

public class ContactMessageTest extends ApiTestsHook {

    public RequestUser requestUserBody;
    public String token;
    public String userId;
    public UserActions userActions;
    public String adminToken;
    public RequestContactMessage requestContactMessageBody;
    public ContactMessageActions contactMessageActions;

    @Test
    public void testMethod() {

        System.out.println("Step 1: New User");
        newUserApi();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Create new user");

        System.out.println("Step 2: Login to obtain token");
        generateTokenLoginUser();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Login as a new user to obtain the auth token");

        System.out.println("Step 3: Post new message as User");
        sendNewMessage();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "New message sent by User");

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

    public void sendNewMessage() {
        PropertyUtility propertyUtility = new PropertyUtility("request-data/contact-message-data");
        requestContactMessageBody = new RequestContactMessage(propertyUtility.getAllData());

        contactMessageActions = new ContactMessageActions();
        contactMessageActions.sendNewMessage(token, requestContactMessageBody);
    }
}

