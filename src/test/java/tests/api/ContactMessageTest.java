package tests.api;

import api.actions.ContactMessageActions;
import api.actions.UserActions;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestReplyMessage;
import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.model.object.data.response.ResponseReplyMessageSuccess;
import core.reporting.ExtentUtility;
import core.reporting.ReportStep;
import core.utils.property.PropertyUtility;
import hooks.ApiTestsHook;
import org.testng.annotations.Test;

import java.io.File;

public class ContactMessageTest extends ApiTestsHook {

    public RequestUser requestUserBody;
    public String token;
    public UserActions userActions;
    public RequestContactMessage requestContactMessageBody;
    public ContactMessageActions contactMessageActions;
    public RequestReplyMessage requestReplyMessageBody;
    public String messageId;
    public String replyId;

    @Test
    public void testMethod() {

        System.out.println("Step 1: Login as Admin to obtain token");
        getAdminToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Login as Admin to obtain the auth token");

        System.out.println("Step 2: Post new message");
        sendNewMessage();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "New message sent by Admin");

        System.out.println("Step 3: Retrieve newly posted message details");
        retrieveSpecificMessageDetails();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get all newly posted message details");

        System.out.println("Step 4: Update status of specific message");
        updateSpecificMessageStatus();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Update status of specific message");

        System.out.println("Step 5: Retrieve updated message details");
        retrieveSpecificMessageDetailsAfterUpdate();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Get the updated message details");

        System.out.println("Step 6: Successfully attach empty file to message");
        attachFileToMessageSuccess();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Attached empty file to message");

        System.out.println("Step 7: Attach invalid file to message (resulting in error)");
        attachFileToMessageFail();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Attach invalid file to message (resulting in error)");

        System.out.println("Step 8: Reply to a specific message");
        replyToMessage();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Reply to a specific message");

        System.out.println("Step 8: Retrieve message details after reply and check new reply ID match");
        retrieveSpecificMessageDetailsAfterReply();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "Retrieve message details after reply and check new reply ID match");

    }


    public void getAdminToken() {
        userActions = new UserActions();

        propertyUtility = new PropertyUtility("request-data/admin-credentials-data");
        requestUserBody = new RequestUser(propertyUtility.getAllData());

        ResponseLoginTokenSuccess responseLoginTokenSuccess = userActions.responseLoginTokenSuccess(requestUserBody);
        token = responseLoginTokenSuccess.getAccessToken();
    }

    public void sendNewMessage() {
        contactMessageActions = new ContactMessageActions();

        propertyUtility = new PropertyUtility("request-data/contact-message-data");
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

    public void attachFileToMessageSuccess() {
        File file = new File("src/test/resources/upload-files/empty-file.txt");
        contactMessageActions.attachFileToMessage(file, token, messageId);
    }

    public void attachFileToMessageFail() {
        File file = new File("src/test/resources/upload-files/invalid-file.txt");
        contactMessageActions.attachFileToMessage(file, token, messageId);
    }

    public void replyToMessage() {
        contactMessageActions = new ContactMessageActions();

        propertyUtility = new PropertyUtility("request-data/reply-message-data");
        requestReplyMessageBody = new RequestReplyMessage(propertyUtility.getAllData());

        ResponseReplyMessageSuccess responseReplyMessageSuccessBody = contactMessageActions.replyToMessage(requestReplyMessageBody, token, messageId);
        replyId = responseReplyMessageSuccessBody.getId();
    }

    public void retrieveSpecificMessageDetailsAfterReply() {
        contactMessageActions.retrieveSpecificMessageDetails(token, messageId, "IN_PROGRESS", replyId);
    }
}

