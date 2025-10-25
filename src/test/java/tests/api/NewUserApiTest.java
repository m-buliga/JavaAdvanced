package tests.api;

import api.model.object.data.request.RequestUser;
import api.model.object.data.response.ResponseUserSuccess;
import core.utils.property.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewUserApiTest {

    @Test
    public void testMethod() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://api.practicesoftwaretesting.com");
        requestSpecification.contentType("application/json");

        // defining the request
        PropertyUtility propertyUtility = new PropertyUtility("request-data/new-user-test-data");
        RequestUser requestUserBody = new RequestUser(propertyUtility.getAllData());
        // requestSpecification.log().body();
        requestSpecification.body(requestUserBody);


        Response response = requestSpecification.post("/users/register");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());

        // response body validation
        // ResponseBody responseBody = response.getBody();
        ResponseUserSuccess responseUserSuccessBody = response.body().as(ResponseUserSuccess.class);
        System.out.println(responseUserSuccessBody.getFirstName());
        Assert.assertEquals(responseUserSuccessBody.getFirstName(), requestUserBody.getFirstName());
    }
}
