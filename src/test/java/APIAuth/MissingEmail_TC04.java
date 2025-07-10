package APIAuth;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class MissingEmail_TC04 {
    RequestSpecification Request3;
    Response response;
@BeforeClass
    public void setupBaseURL(){
        RestAssured.baseURI="https://reqres.in/";
        Request3= given()
                .header("Content-Type","application/json")
                .header("x-api-key","reqres-free-v1");
    }

    @Test
    public void WrongEmailPostMethod(){
        JSONObject Paramet=new JSONObject();
        Paramet.put("password","WrongPassword");
        response=Request3
                .body(Paramet.toString())
                .when()
                .post("api/login");
        response.then().log().all();
         System.out.println("the actual status code is " +response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400, "The email field is missing");
        String error=response.jsonPath().getString("error");
        Assert.assertEquals(error,"Missing email or username", "Email field is not aadded request ");

    }

}
