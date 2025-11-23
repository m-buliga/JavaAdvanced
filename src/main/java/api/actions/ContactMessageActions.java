package api.actions;

import api.base.ResponseStatuses;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.model.object.data.response.ResponseGetContactMessageSuccess;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.model.object.data.response.ResponseUpdateContactMessageSuccess;
import api.services.ServiceImplementation.ContactMessageImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

public class ContactMessageActions {

    private ContactMessageImplementation contactMessageImplementation;

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
}
