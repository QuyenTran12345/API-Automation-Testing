import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
public class Test_01_GET {
    @Test
    void test_01() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        int status = response.getStatusCode();
        Assert.assertEquals(status, 200);
    }

    @Test
    void test_02() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    @Test
    void test_03() {
        RequestSpecification request = RestAssured.given();
        // JSONObject is a class that represents a Simple JSON.

        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "pistol");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("https://reqres.in/api/register");

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);
        JSONObject expected = new JSONObject();
        expected.put("id", 4);
        expected.put("token", "QpwL5tke4Pnpja7X4");

        String successResult = response.body().print();
        Assert.assertEquals(successResult, expected.toJSONString());
    }

}
// Document https://github.com/rest-assured/rest-assured/wiki/Usage#static-imports