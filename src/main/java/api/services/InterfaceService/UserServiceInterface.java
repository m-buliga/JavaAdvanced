package api.services.InterfaceService;

import api.model.object.data.request.RequestUser;
import io.restassured.response.Response;

public interface UserServiceInterface {
    // this interface represents the actions we want to execute in this service (User service)

    Response reqisterUser(RequestUser body);
    Response generateTokenLoginUser(RequestUser body);
    Response retrieveUserDetails(String token, String userId);
    Response deleteUser(String token, String userId);

}
