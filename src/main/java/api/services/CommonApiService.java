package api.services;

import api.base.ApiRestBase;
import api.base.RequestMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonApiService {

    // 2nd layer: define actions on client configurations from layer 1
    public Response post(Object body, String endpoint) {
        // method for a POST with body

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);
        Response response = performRequest(RequestMethods.REQUEST_POST, requestSpecification, endpoint);
        return response;
    }

    public Response get(String token, String endpoint) {
        // method for a GET method

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.header("Authorization", "Bearer" + token);
        Response response = performRequest(RequestMethods.REQUEST_GET, requestSpecification, endpoint);
        return response;
    }

    // method that instantiates the link with layer 1
    private Response performRequest(String requestMethod, RequestSpecification requestSpecification, String endpoint) {
        return new ApiRestBase().performRequest(requestMethod, requestSpecification, endpoint);
    }

}
