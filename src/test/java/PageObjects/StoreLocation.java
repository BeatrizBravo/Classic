package PageObjects;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class StoreLocation {


    public String testRestAssured() {
//1.SetthebaseURI
        RestAssured.baseURI = "http://3.11.77.136";

//2.SendanHTTPGETrequestto/index.phpandextracttheresponse
        Response response = given()
                .when()
                .get("/index.php?controller=stores")
                .then()
                .contentType(ContentType.HTML)//ValidatableResponseOptionsmethods.
                .assertThat()
                .statusCode(200)
                .body("html.head.title", equalTo("Stores"))
                .log().all()
                .extract().response();
        return "Storepagefound";
    }
}