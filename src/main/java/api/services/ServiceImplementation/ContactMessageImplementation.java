package api.services.ServiceImplementation;

import api.model.object.data.request.RequestContactMessage;
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
}
