package APIAuth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestInvalidLogin_TC02 {
    RequestSpecification Resquest1;
    Response response;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI="https://reqres.in/";
        Resquest1=given()
                .header("Content-Type","application/json")
                .header("x-api-key","reqres-free-v1");
    }

    @Test
    public void WrongCredentialsPostMethod(){
        JSONObject Param=new JSONObject();
        Param.put("email","rong.email@reqres.in");
        Param.put("password","wrongpassword");
        response = Resquest1
                .body(Param.toString())
                .when()
                .post("api/login/");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),400, "the crendetials are wrong so actual and expected is 400");
        System.out.println("The status code is " +response.getStatusCode());

        String error = response.jsonPath().getString("error");
        System.out.println("the error is " +error);
        Assert.assertEquals(error,"user not found", "email or password is wrong");

    }
}
