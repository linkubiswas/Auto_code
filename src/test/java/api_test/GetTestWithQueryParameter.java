package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithQueryParameter extends BaseAPIClass{

    @Test
    public void getUserQueryParameter(){

        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.queryParam("page","2").request(Method.GET);
        LOGGER.debug(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = response.jsonPath();
        List<String> list = jsonPath.get("data.email");
        String expectedEmail = "michael.lawson@reqres.in";
        boolean emailExist = list.contains(expectedEmail);
        Assert.assertTrue(emailExist, expectedEmail + "dose not exits");
    }
}
