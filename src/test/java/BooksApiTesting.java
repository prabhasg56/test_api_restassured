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
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
        eachBooksDetails.put("Book Id", 1);
        eachBooksDetails.put("Book Name", "Java the Complete Reference");
        booksDetails.add(eachBooksDetails);
        eachBooksDetails.put("Book Id", 2);
        eachBooksDetails.put("Book Name", "Data Structures and Algorithms");
        booksDetails.add(eachBooksDetails);
        //System.out.println(booksDetails);
        JSONObject jsonObject = new JSONObject();
        RestAssured.given()
                .header("content_Type", "Application/JSOn")
                .body(eachBooksDetails)
                .when()
                .post("https://simple-books-api.glitch.me")
                .then()
                .statusCode(201);


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
                delete("https://simple-books-api.glitch.me").
                then().
                statusCode(204).log().all();
    }


}
