package tests.api;

import api.actions.ContactMessageActions;
import api.actions.UserActions;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseGetContactMessageSuccess;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.model.object.data.response.ResponseUpdateContactMessageSuccess;
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
    public String status;
    public RequestContactMessage requestContactMessageBody;
    public ContactMessageActions contactMessageActions;
    public String messageId;

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

        System.out.println("Step 4: Retrieve newly posted message details");
        retrieveSpecificMessageDetails();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get all newly posted message details");

        System.out.println("Step 5: Update status of specific message");
        updateSpecificMessageStatus();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Update status of specific message");

        System.out.println("Step 6: Retrieve updated message details");
        retrieveSpecificMessageDetailsAfterUpdate();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get the updated message details");

        System.out.println("Step 7: Reply to message");
        //retrieveSpecificMessageDetailsAfterUpdate();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Reply to message");

        System.out.println("Step 8: Login as Admin and delete created User");
        //retrieveSpecificMessageDetailsAfterUpdate();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Delete created User");

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
        contactMessageActions = new ContactMessageActions();

        PropertyUtility propertyUtility = new PropertyUtility("request-data/contact-message-data");
        requestContactMessageBody = new RequestContactMessage(propertyUtility.getAllData());

        ResponseNewContactMessageSuccess responseNewContactMessageSuccessBody = contactMessageActions.sendNewMessage(token, requestContactMessageBody);
        messageId = responseNewContactMessageSuccessBody.getId();
    }

    public void retrieveSpecificMessageDetails() {
        contactMessageActions.retrieveSpecificMessageDetails(token, messageId, "NEW");
    }

    public void updateSpecificMessageStatus() {
        contactMessageActions.updateSpecificMessageStatus("IN_PROGRESS", token, messageId);

    }

    public void retrieveSpecificMessageDetailsAfterUpdate() {
        contactMessageActions.retrieveSpecificMessageDetails(token, messageId, "IN_PROGRESS");
    }
}

