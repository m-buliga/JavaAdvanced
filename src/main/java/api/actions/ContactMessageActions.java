package api.actions;

import api.base.ResponseStatuses;
import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.response.ResponseNewContactMessageSuccess;
import api.services.ServiceImplementation.ContactMessageImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

public class ContactMessageActions {

    private ContactMessageImplementation contactMessageImplementation;

    public ContactMessageActions() {
        contactMessageImplementation = new ContactMessageImplementation();
    }

    public void sendNewMessage(String token, RequestContactMessage requestContactMessage) {
        Response response = contactMessageImplementation.sendNewMessage(requestContactMessage, token);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseNewContactMessageSuccess responseNewContactMessageSuccess = response.body().as(ResponseNewContactMessageSuccess.class);
        responseNewContactMessageSuccess.validateNotNullFields();
        Assert.assertEquals(responseNewContactMessageSuccess.getStatus(), "NEW");

    }
}
