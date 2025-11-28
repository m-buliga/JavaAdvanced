package api.actions;

import api.base.ResponseStatuses;
import api.model.object.data.MessageReplyObject;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestReplyMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.model.object.data.response.ResponseAttachFileMessageFailed;
import api.model.object.data.response.ResponseGetContactMessageSuccess;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.model.object.data.response.ResponseReplyMessageFailed;
import api.model.object.data.response.ResponseReplyMessageSuccess;
import api.model.object.data.response.ResponseUpdateContactMessageSuccess;
import api.services.serviceImplementation.ContactMessageImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.util.List;

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

    public ResponseGetContactMessageSuccess retrieveSpecificMessageDetails(String token, String messageId, String expectedStatus, String expectedReplyId) {
        Response response = contactMessageImplementation.retrieveSpecificMessageDetails(token, messageId);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseGetContactMessageSuccess responseGetContactMessageSuccess = response.body().as(ResponseGetContactMessageSuccess.class);
        responseGetContactMessageSuccess.validateNotNullFields();
        Assert.assertEquals(responseGetContactMessageSuccess.getStatus(), expectedStatus);


        if (expectedReplyId != null && !expectedReplyId.isEmpty()) {
            List<MessageReplyObject> replies = responseGetContactMessageSuccess.getReplies();
            Assert.assertNotNull(replies, "Replies list should not be null");
            Assert.assertFalse(replies.isEmpty(), "Replies list should not be empty");

            boolean found = replies.stream().anyMatch(reply -> expectedReplyId.equals(reply.getId()));
            Assert.assertTrue(found, "Expected reply with ID " + expectedReplyId + " not found in replies");
        }

        return responseGetContactMessageSuccess;
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
            Assert.fail("Unexpected status code " + statusCode);
        }
    }

    public ResponseReplyMessageSuccess replyToMessage(RequestReplyMessage body, String token, String messageId) {
        Response response = contactMessageImplementation.replyToMessage(body, token, messageId);
        ResponseReplyMessageSuccess responseReplyMessageSuccess = response.body().as(ResponseReplyMessageSuccess.class);

        int statusCode = response.getStatusCode();

        if (statusCode == ResponseStatuses.STATUS_CODE_CREATED) {
            Assert.assertEquals(statusCode, ResponseStatuses.STATUS_CODE_CREATED);
            responseReplyMessageSuccess.validateNotNullFields();


        } else if (statusCode == ResponseStatuses.STATUS_CODE_INTERNAL_SERVER_ERROR) {
            Assert.assertEquals(statusCode, ResponseStatuses.STATUS_CODE_INTERNAL_SERVER_ERROR);

            ResponseReplyMessageFailed responseReplyMessageFailed =
                    response.body().as(ResponseReplyMessageFailed.class);

            System.out.println("Error returned: " + responseReplyMessageFailed.getMessage());

            responseReplyMessageFailed.validateNotNullFields();
            Assert.assertEquals(responseReplyMessageFailed.getMessage(), "Something went wrong");


        } else {
            Assert.fail("Unexpected status code " + statusCode);

        }
        return responseReplyMessageSuccess;
    }

}
