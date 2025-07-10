package APIAuth;

import com.aventstack.extentreports.gherkin.model.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MissingPAssword_TC03 {

    RequestSpecification Request2;
    Response response;

    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://reqres.in/";
        Request2 = given()
                  .header("Content-Type","application/json")
                  .header("x-api-key","reqres-free-v1");
    }

    @Test
    public void WrongPasswordPostMethod(){
        JSONObject Par=new JSONObject();
        Par.put("email","eve.holt@reqres.in");
        response =  Request2
                .body(Par.toString())
                .when()
                .post("api/login");
        response.then().log().all();

        System.out.println("the status code is " +response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),400, "the missing the username or password");

        String error=response.jsonPath().getString("error");
        System.out.println("the error is " +error);
        Assert.assertEquals(error, "Missing password", "password field is not mentioned");


    }
}
