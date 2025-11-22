package api.services.InterfaceService;

import api.model.object.data.request.RequestContactMessage;
import io.restassured.response.Response;

public interface ContactMessageInterface {

    // this interface represents actions that this service contact messages can do

    Response sendNewMessage(RequestContactMessage body, String token);

}
