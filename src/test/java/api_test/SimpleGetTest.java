package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleGetTest extends BaseAPIClass{

    @Test
    public void getAllUsers(){

        //Specify the base URL or endpoint of the rest API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Create an object from RequestSpecification class in order to send the request to the server
        //Added the server URL to make the request
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server and this will return the response
       Response response = httpRequest.request(Method.GET);
       //asString() or prettyPrint()
       LOGGER.debug(response.getBody().asString());

        //Validating response code is 200
        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();
      List<String> list = jsonPath.get("data.email");
        //System.out.println(list);
        String expectedEmail = "george.bluth@reqres.in";
        boolean emailExist = list.contains(expectedEmail);
        Assert.assertTrue(emailExist, expectedEmail + "dose not exist");

    }
}
