import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    @Test
    void test1(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Status code : "+response.getStatusCode());
        System.out.println("Response : "+response.asPrettyString());
        System.out.println("Body : "+response.getBody().asString());
        System.out.println("Body : "+response.getBody().asPrettyString());
        System.out.println("Time taken :"+response.getTime());
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);

    }
    @Test
    void test2(){
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    }
}
