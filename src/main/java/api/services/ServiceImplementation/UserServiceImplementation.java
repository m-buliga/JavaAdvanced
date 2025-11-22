package api.services.ServiceImplementation;

import api.model.object.data.request.RequestUser;
import api.services.ApiService.UserApiService;
import api.services.InterfaceService.UserServiceInterface;
import io.restassured.response.Response;

public class UserServiceImplementation implements UserServiceInterface {
    // create an instance of ApiService to be able to access the general methods

    private UserApiService userApiService;

    @Override
    public Response reqisterUser(RequestUser body) {
        userApiService = new UserApiService();
        return userApiService.post(body, "/users/register");
    }

    @Override
    public Response generateTokenLoginUser(RequestUser body) {
        userApiService = new UserApiService();
        return userApiService.post(body, "/users/login");
    }

    @Override
    public Response retrieveUserDetails(String token, String userId) {
        userApiService = new UserApiService();
        String endpoint = "/users/" + userId;
        return userApiService.get(token, endpoint);
    }

    @Override
    public Response deleteUserAsUser(String token, String userId) {
        userApiService = new UserApiService();
        String endpoint = "/users/" + userId;
        return userApiService.delete(token, endpoint);
    }

    public Response deleteUserAsAdmin(String adminToken, String userId) {
        userApiService = new UserApiService();
        String endpoint = "/users/" + userId;
        return userApiService.delete(adminToken, endpoint);
    }
}
