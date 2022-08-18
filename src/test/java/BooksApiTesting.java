import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;

public class BooksApiTesting {

    @Test
    public void testPostBooksDetails() {
        Map<String, Object> eachBooksDetails = new HashMap<>();
        List<Map> booksDetails = new ArrayList<>();
        RestAssured.baseURI = "https://reqres.in";
        eachBooksDetails.put("Book Id", 1);
        eachBooksDetails.put("Book Name", "Java the Complete Reference");
        booksDetails.add(eachBooksDetails);
        eachBooksDetails.put("Book Id", 2);
        eachBooksDetails.put("Book Name", "Data Structures and Algorithms");
        booksDetails.add(eachBooksDetails);
        RestAssured.given()
                .header("content_Type", "Application/JSOn")
                .body(booksDetails)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }
    @Test
    public void testPutBooksDetails(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name","Prabhas");
        jsonObject.put("Job","Teacher");
        //System.out.println(jsonObject);
        given().
                header("Content-Type","Application/JSON").
                accept(ContentType.JSON).
                body(jsonObject).
        when().
                put("https://reqres.in/api/users/2").
        then().
                assertThat().statusCode(200);
    }
    @Test
    public void testPatchBooksDetails(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Name","Prabhas");
        jsonObject.put("Job","Teacher");
        //System.out.println(jsonObject);
        given().
                header("Content-Type","Application/JSON").
                accept(ContentType.JSON).
                body(jsonObject).
                when().
                patch("https://reqres.in/api/users/2").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void testGetBooksDetails() {
        Response holeContents = RestAssured.get("https://simple-books-api.glitch.me");
        System.out.println(holeContents.asPrettyString());
        int statusCode = holeContents.statusCode();
        Assert.assertEquals(statusCode, 200);

        Response books = RestAssured.get("https://simple-books-api.glitch.me/books");
        System.out.println(books.statusCode());
        int booksStatusCode = holeContents.statusCode();
        Assert.assertEquals(booksStatusCode, 200);

    }

    @Test
    public void testDeleteBooks() {
        when().
                delete("https://reqres.in/api/users/2").
                then().
                statusCode(204).log().all();
    }
}
