package core.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServicesHelper {

    // method to log info about a request
    public static void requestLogs(RequestSpecification requestSpecification, String path, String methodType) {
        LoggerUtility.infoTest("==== Request Info ====");
        LoggerUtility.infoTest(getRequestUrl(path));
        LoggerUtility.infoTest(getRequestMethod(methodType));
        LoggerUtility.infoTest(getRequestBody(requestSpecification));
    }

    public static void responseLogs(Response response) {
        LoggerUtility.infoTest("==== Response Info ====");
        LoggerUtility.infoTest(getResponseStatusLine(response));
        LoggerUtility.infoTest(getResponseStatusCode(response));
        LoggerUtility.infoTest(getResponseTime(response));
        LoggerUtility.infoTest(getResponseBody(response));
    }

    private static String getRequestUrl(String path) {
        return "Request URI: https://api.practicesoftwaretesting.com" + path;
    }

    private static String getRequestMethod(String methodType) {
        return "Request METHOD: " + methodType;
    }

    //@SneakyThrows(IOException.class)
    private static String getRequestBody(RequestSpecification requestSpecification) {
        String json = "";
        Object requestBody = ((RequestSpecificationImpl) requestSpecification).getBody();
        if (requestBody != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.readTree(requestBody.toString()).toPrettyString();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "Request BODY: \n" + json;
    }

    public static String getResponseStatusLine(Response response) {
        return "Response STATUS: " + response.getStatusLine();
    }

    public static String getResponseStatusCode(Response response) {
        return "Response STATUS CODE: " + response.getStatusCode();
    }

    public static String getResponseTime(Response response) {
        return "Response TIME: " + response.getTime();
    }

    public static String getResponseBody(Response response) {
        if (response.getBody() != null) {
            return "Response BODY: " + response.getBody().asPrettyString();
        }
        else {
            return "";
        }
    }


    // method to log info about a response

}
