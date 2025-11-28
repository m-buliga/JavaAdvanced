package api.services.serviceImplementation;

import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestReplyMessage;
import api.model.object.data.request.RequestUpdateMessage;
import api.services.apiService.ContactMessagesService;
import api.services.endpoints.ContactMessageEndpoints;
import api.services.interfaceService.ContactMessageInterface;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


import java.io.File;


public class ContactMessageImplementation implements ContactMessageInterface {

    private final ContactMessagesService contactMessagesService;

    public ContactMessageImplementation() {
        contactMessagesService = new ContactMessagesService();
    }

    @Override
    public Response sendNewMessage(RequestContactMessage body, String token) {
        return contactMessagesService.post(body, ContactMessageEndpoints.MESSAGE_CREATE, token);
    }

    @Override
    public Response retrieveSpecificMessageDetails(String token, String messageId) {
        //String endpoint = "/messages/" + messageId;
        String endpoint = ContactMessageEndpoints.MESSAGE_BY_ID + messageId;
        return contactMessagesService.get(token, endpoint);
    }

    @Override
    public Response replyToMessage(RequestReplyMessage body, String token, String messageId) {
        String endpoint = ContactMessageEndpoints.MESSAGE_REPLY + messageId + "/reply";
        return contactMessagesService.post(body, endpoint, token);
    }

    @Override
    public Response updateSpecificMessageStatus(RequestUpdateMessage body, String token, String messageId) {
        String endpoint = ContactMessageEndpoints.MESSAGE_UPDATE_STATUS + messageId + "/status";
        return contactMessagesService.put(body, endpoint, token);
    }

    @Override
    public Response attachFileToMessage(File file, String token, String messageId) {
        String endpoint = ContactMessageEndpoints.MESSAGE_ATTACH_FILE + messageId + "/attach-file";
        return given()
                .header("Authorization", "Bearer " + token)
                .multiPart("file", file)
                .log().all()
                .post("https://api.practicesoftwaretesting.com" + endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
