package api.services.interfaceService;

import api.model.object.data.request.RequestContactMessage;
import api.model.object.data.request.RequestReplyMessage;
import api.model.object.data.request.RequestUpdateMessage;
import io.restassured.response.Response;

import java.io.File;

public interface ContactMessageInterface {

    // this interface represents actions that this service contact messages can do

    Response sendNewMessage(RequestContactMessage body, String token);
    Response retrieveSpecificMessageDetails(String token, String messageId);
    Response replyToMessage(RequestReplyMessage body, String token, String messageId);
    Response updateSpecificMessageStatus(RequestUpdateMessage body, String token, String messageId);
    Response attachFileToMessage(File file, String token, String messageId);
}
