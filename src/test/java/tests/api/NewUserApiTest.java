package tests.api;

import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseLoginTokenSuccess;
import api.model.object.data.response.ResponseUserSuccess;
import core.utils.property.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewUserApiTest {

    public RequestUser requestUserBody;
    public String token;
    public String userId;


    @Test
    public void testMethod() {
        System.out.println("Step 1: New User");
        newUserApi();
        System.out.println("----------------");

        System.out.println("Step 2: Login to obtain token");
        generateTokenLoginUser();
        System.out.println("----------------");

        System.out.println("Step 3: Retrieve user details");
        retrieveUserDetails();
    }

    public void newUserApi() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.practicesoftwaretesting.com");
        requestSpecification.contentType("application/json");

        // defining the request
        PropertyUtility propertyUtility = new PropertyUtility("request-data/new-user-test-data");
        requestUserBody = new RequestUser(propertyUtility.getAllData());
        // requestSpecification.log().body();
        requestSpecification.body(requestUserBody);


        Response response = requestSpecification.post("/users/register");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 201);

        // response body validation
        // ResponseBody responseBody = response.getBody();
        ResponseUserSuccess responseUserSuccessBody = response.body().as(ResponseUserSuccess.class);
        System.out.println("UserId = " + responseUserSuccessBody.getId());
        userId = responseUserSuccessBody.getId();

        Assert.assertEquals(responseUserSuccessBody.getFirstName(), requestUserBody.getFirstName());
    }

    public void generateTokenLoginUser() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.practicesoftwaretesting.com");
        requestSpecification.contentType("application/json");

        requestSpecification.body(requestUserBody);
        Response response = requestSpecification.post("/users/login");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        // System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseLoginTokenSuccess responseLoginTokenSuccess = response.body().as(ResponseLoginTokenSuccess.class);
        System.out.println("Token = " + responseLoginTokenSuccess.getAccessToken());
        token = responseLoginTokenSuccess.getAccessToken();
    }

    public void retrieveUserDetails() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.practicesoftwaretesting.com");
        requestSpecification.contentType("application/json");
        requestSpecification.header("Authorization", "Bearer" + token);

        Response response = requestSpecification.get("/users/" + userId);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseUserSuccess responseUserSuccess = response.body().as(ResponseUserSuccess.class);
        System.out.println(responseUserSuccess.getId());
        System.out.println(userId);
        Assert.assertEquals(responseUserSuccess.getId(), userId);
    }

}
