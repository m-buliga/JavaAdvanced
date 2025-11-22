package api.services;

import api.base.ApiRestBase;
import api.base.RequestMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import core.logging.ServicesHelper;

public class CommonApiService {

    // 2nd layer: define actions on client configurations from layer 1
    public Response post(Object body, String endpoint) {

        // method for a POST with body
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);
        ServicesHelper.requestLogs(requestSpecification, endpoint, RequestMethods.REQUEST_POST);

        Response response = performRequest(RequestMethods.REQUEST_POST, requestSpecification, endpoint);
        ServicesHelper.responseLogs(response);
        return response;
    }

    public Response post(Object body, String endpoint, String token) {

        // method for a POST with body and token
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer" + token);
        requestSpecification.body(body);
        ServicesHelper.requestLogs(requestSpecification, endpoint, RequestMethods.REQUEST_POST);

        Response response = performRequest(RequestMethods.REQUEST_POST, requestSpecification, endpoint);
        ServicesHelper.responseLogs(response);
        return response;
    }

    public Response get(String token, String endpoint) {

        // function for a GET method
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer" + token);
        ServicesHelper.requestLogs(requestSpecification, endpoint, RequestMethods.REQUEST_GET);

        Response response = performRequest(RequestMethods.REQUEST_GET, requestSpecification, endpoint);
        ServicesHelper.responseLogs(response);
        return response;
    }

    public Response delete(String token, String endpoint) {

        // function for a DELETE method
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer" + token);
        ServicesHelper.requestLogs(requestSpecification, endpoint, RequestMethods.REQUEST_DELETE);


        Response response = performRequest(RequestMethods.REQUEST_DELETE, requestSpecification, endpoint);
        ServicesHelper.responseLogs(response);
        return response;
    }

    // method that instantiates the link with layer 1
    private Response performRequest(String requestMethod, RequestSpecification requestSpecification, String endpoint) {
        return new ApiRestBase().performRequest(requestMethod, requestSpecification, endpoint);
    }

}
