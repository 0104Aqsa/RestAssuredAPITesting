package APIAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class TestValidLogin_TC01 {

RequestSpecification Request;
@BeforeClass
public void setUp() {
RestAssured.baseURI="https://reqres.in/";
    Request = given()
            .header("content-Type", "application/json")
            .header("x-api-key", "reqres-free-v1");

}
@Test
public void ValidLoginPostMethod(){
    JSONObject Parameter=new JSONObject();
    Parameter.put("email","eve.holt@reqres.in");
    Parameter.put("password","cityslicka");

    Response response =Request
            .body(Parameter.toString())
            .when()
            .post("api/login");
    //log complete for check
    response.then().log().all();
// Check status code
    System.out.println("The Actual Status code after post login method is " +response.getStatusCode());
// Define the token to assert in to check actual and expected is same
    String token = response.jsonPath().getString("token");
    //check token actual getting
System.out.println("The actual token getting is in response is " +token);

Assert.assertEquals(response.getStatusCode(),200,"Status code is 200 its sucess");
Assert.assertEquals(token, "QpwL5tke4Pnpja7X4","actual and expected token is same" );
}
}
