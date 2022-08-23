package api_test;


import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePatchTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePatchTest.class);

    @Test
    public void updateUserSingleField(){
        LOGGER.info("--------API Test: Update User's Single Field--------");
        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        Faker faker = new Faker();
        String userRole = faker.job().title();
        LOGGER.debug("User Job: " + userRole);


        JSONObject reqBody = new JSONObject();
        reqBody.put("job", userRole);

        httpRequest.header("Content-type", "application/json");
        httpRequest.body(reqBody.toJSONString());

        String id = "2";

        Response response = httpRequest.request(Method.PATCH, id);
        LOGGER.debug(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(),200);

        JsonPath jsonPath = response.jsonPath();
        String actualJob = jsonPath.getString("job");
        Assert.assertEquals(actualJob, userRole);


        LOGGER.info("--------End Test: Update User's Single Field--------");
    }
}
