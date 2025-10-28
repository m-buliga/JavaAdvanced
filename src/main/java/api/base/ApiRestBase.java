package api.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRestBase {

    // 1st layer: rest client configuration

    private RequestSpecification prepareSpecification(RequestSpecification requestSpecification) {

        requestSpecification.baseUri("https://api.practicesoftwaretesting.com");
        requestSpecification.contentType("application/json");

        return requestSpecification;
    }

    public Response performRequest(String requestMethod, RequestSpecification requestSpecification, String endpoint) {
        switch (requestMethod) {
            case RequestMethods.REQUEST_POST:
                return prepareSpecification(requestSpecification).post(endpoint);
            case RequestMethods.REQUEST_PUT:
                return prepareSpecification(requestSpecification).put(endpoint);
            case RequestMethods.REQUEST_PATCH:
                return prepareSpecification(requestSpecification).patch(endpoint);
            case RequestMethods.REQUEST_GET:
                return prepareSpecification(requestSpecification).get(endpoint);
            case RequestMethods.REQUEST_DELETE:
                return prepareSpecification(requestSpecification).delete(endpoint);
        }
        return null;
    }
}
