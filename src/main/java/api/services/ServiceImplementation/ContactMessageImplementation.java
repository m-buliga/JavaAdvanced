package api.services.ServiceImplementation;

import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestReplyMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.services.ApiService.ContactMessagesService;
import api.services.ApiService.UserApiService;
import api.services.InterfaceService.ContactMessageInterface;
import io.restassured.response.Response;

public class ContactMessageImplementation implements ContactMessageInterface {

    private ContactMessagesService contactMessagesService;

    @Override
    public Response sendNewMessage(RequestContactMessage body, String token) {
        contactMessagesService = new ContactMessagesService();
        return contactMessagesService.post(body, "/messages", token);
    }

    @Override
    public Response retrieveSpecificMessageDetails(String token, String messageId) {
        return null;
    }

    @Override
    public Response replyToMessage(RequestReplyMessage body, String token, String messageId) {
        return null;
    }


    @Override
    public Response updateSpecificMessageStatus(RequestUpdateMessage body, String token, String messageId) {
        return null;
    }
}
