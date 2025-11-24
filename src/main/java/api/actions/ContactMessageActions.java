package api.actions;

import api.base.ResponseStatuses;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.model.object.data.response.ResponseAttachFileMessageFailed;
import api.model.object.data.response.ResponseDeleteUserFailed;
import api.model.object.data.response.ResponseGetContactMessageSuccess;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.model.object.data.response.ResponseUpdateContactMessageSuccess;
import api.services.serviceImplementation.ContactMessageImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;

public class ContactMessageActions {

    private final ContactMessageImplementation contactMessageImplementation;

    public ContactMessageActions() {
        contactMessageImplementation = new ContactMessageImplementation();
    }

    public ResponseNewContactMessageSuccess sendNewMessage(String token, RequestContactMessage requestContactMessage) {
        Response response = contactMessageImplementation.sendNewMessage(requestContactMessage, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseNewContactMessageSuccess responseNewContactMessageSuccessBody = response.body().as(ResponseNewContactMessageSuccess.class);
        responseNewContactMessageSuccessBody.validateNotNullFields();
        Assert.assertEquals(responseNewContactMessageSuccessBody.getStatus(), "NEW");

        return responseNewContactMessageSuccessBody;
    }

    public void updateSpecificMessageStatus(String newStatus, String token, String messageId) {
        RequestUpdateMessage requestUpdateMessage = new RequestUpdateMessage(newStatus);

        Response response = contactMessageImplementation.updateSpecificMessageStatus(requestUpdateMessage, token, messageId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseUpdateContactMessageSuccess responseUpdateContactMessageSuccessBody = response.body().as(ResponseUpdateContactMessageSuccess.class);
        responseUpdateContactMessageSuccessBody.validateNotNullFields();
        Assert.assertEquals(responseUpdateContactMessageSuccessBody.getSuccess(), true);
    }

    public void retrieveSpecificMessageDetails(String token, String messageId, String expectedStatus) {
        Response response = contactMessageImplementation.retrieveSpecificMessageDetails(token, messageId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseGetContactMessageSuccess responseGetContactMessageSuccess = response.body().as(ResponseGetContactMessageSuccess.class);
        responseGetContactMessageSuccess.validateNotNullFields();
        Assert.assertEquals(responseGetContactMessageSuccess.getStatus(), expectedStatus);

    }

    public void attachFileToMessage(File file, String token, String messageId) {
        Response response = contactMessageImplementation.attachFileToMessage(file, token, messageId);

        int statusCode = response.getStatusCode();

        if (statusCode == ResponseStatuses.STATUS_CODE_OK) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

            ResponseUpdateContactMessageSuccess responseUpdateContactMessageSuccessBody = response.body().as(ResponseUpdateContactMessageSuccess.class);
            Assert.assertEquals(responseUpdateContactMessageSuccessBody.getSuccess(), true);
            System.out.println("Attached empty file: Successful operation");

        } else if (statusCode == ResponseStatuses.STATUS_CODE_BAD_REQUEST) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_BAD_REQUEST);

            ResponseAttachFileMessageFailed responseAttachFileMessageFailed = response.body().as(ResponseAttachFileMessageFailed.class);
            String error = responseAttachFileMessageFailed.getErrors().get(0);
            System.out.println(error);

            responseAttachFileMessageFailed.validateNotNullFields();
            Assert.assertEquals(error, "Currently we only allow empty files.");

        } else {
            Assert.fail("11Unexpected status code " + statusCode);
        }
    }
}
