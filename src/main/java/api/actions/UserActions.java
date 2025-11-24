package api.actions;

import api.base.ResponseStatuses;
import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseDeleteUserFailed;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseUserFailed;
import api.model.object.data.response.ResponseUserSuccess;
import api.services.serviceImplementation.UserServiceImplementation;
import io.restassured.response.Response;
import org.testng.Assert;

public class UserActions {

    private final UserServiceImplementation userServiceImplementation;

    public UserActions() {
        userServiceImplementation = new UserServiceImplementation();
    }

    public ResponseUserSuccess createNewUser(RequestUser requestUser) {
        Response response = userServiceImplementation.reqisterUser(requestUser);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_CREATED);

        ResponseUserSuccess responseUserSuccessBody = response.body().as(ResponseUserSuccess.class);
        responseUserSuccessBody.validateNotNullFields();
        Assert.assertEquals(responseUserSuccessBody.getFirstName(), requestUser.getFirstName());

        return responseUserSuccessBody;
    }

    public ResponseLoginTokenSuccess responseLoginTokenSuccess(RequestUser requestUser) {
        Response response = userServiceImplementation.generateTokenLoginUser(requestUser);
        Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_OK);

        ResponseLoginTokenSuccess responseLoginTokenSuccess = response.body().as(ResponseLoginTokenSuccess.class);
        responseLoginTokenSuccess.validateNotNullFields();

        return responseLoginTokenSuccess;

    }

    public void retrieveUserDetails(String token, String userId) {
        Response response = userServiceImplementation.retrieveUserDetails(token, userId);
        int statusCode = response.getStatusCode();

        if (statusCode == ResponseStatuses.STATUS_CODE_OK) {
            Assert.assertEquals(statusCode, ResponseStatuses.STATUS_CODE_OK);

            ResponseUserSuccess responseUserSuccess = response.body().as(ResponseUserSuccess.class);
            responseUserSuccess.validateNotNullFields();
            Assert.assertEquals(responseUserSuccess.getId(), userId);

        } else if (statusCode == ResponseStatuses.STATUS_CODE_NOT_FOUND) {
            Assert.assertEquals(statusCode, ResponseStatuses.STATUS_CODE_NOT_FOUND);

            ResponseUserFailed responseUserFailed = response.body().as(ResponseUserFailed.class);
            String error = responseUserFailed.getError();
            System.out.println("Message returned: " + responseUserFailed.getMessage());
            System.out.println("Error returned: " + responseUserFailed.getError());

            responseUserFailed.validateNotNullFields();
            Assert.assertTrue(error.contains("No query results"));

        } else {
            Assert.fail("Unexpected status code " + statusCode);
        }

    }

    public void deleteUser(String token, String userId) {

        Response response = userServiceImplementation.deleteUser(token, userId);

        int statusCode = response.getStatusCode();

        if (statusCode == ResponseStatuses.STATUS_CODE_NO_CONTENT) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_NO_CONTENT);
            System.out.println("Deleted user with Admin role: Successful operation");

        } else if (statusCode == ResponseStatuses.STATUS_CODE_FORBIDDEN) {
            Assert.assertEquals(response.getStatusCode(), ResponseStatuses.STATUS_CODE_FORBIDDEN);

            ResponseDeleteUserFailed responseDeleteUserFailed = response.body().as(ResponseDeleteUserFailed.class);
            String message = responseDeleteUserFailed.getMessage();
            System.out.println(message + " - Admin role is required to delete a specific user");

            responseDeleteUserFailed.validateNotNullFields();
            Assert.assertTrue(message.contains("Forbidden"));

        } else {
            Assert.fail("Unexpected status code " + statusCode);
        }
    }

}
