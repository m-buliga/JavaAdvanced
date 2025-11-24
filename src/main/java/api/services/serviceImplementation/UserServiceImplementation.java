package api.services.serviceImplementation;

import api.model.object.data.request.RequestUser;
import api.services.apiService.UserApiService;
import api.services.endpoints.UserEndpoints;
import api.services.interfaceService.UserServiceInterface;
import io.restassured.response.Response;

public class UserServiceImplementation implements UserServiceInterface {
    // create an instance of ApiService to be able to access the general methods

    private final UserApiService userApiService;

    public UserServiceImplementation() {
        userApiService = new UserApiService();
    }

    @Override
    public Response reqisterUser(RequestUser body) {
        return userApiService.post(body, UserEndpoints.USER_CREATE);
    }

    @Override
    public Response generateTokenLoginUser(RequestUser body) {
        return userApiService.post(body, UserEndpoints.USER_LOGIN);
    }

    @Override
    public Response retrieveUserDetails(String token, String userId) {
        String endpoint = UserEndpoints.USER_GET_BY_ID + userId;
        return userApiService.get(token, endpoint);
    }

    @Override
    public Response deleteUser(String token, String userId) {
        String endpoint = UserEndpoints.USER_DELETE_BY_ID + userId;
        return userApiService.delete(token, endpoint);
    }

}
