import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class getHomePage {

    @Test
    public void testRestAssured() {
        // 1. Set the base URI
        RestAssured.baseURI = "http://3.11.77.136";

        // 2. Send an HTTP GET request to /index.php and extract the response
        Response response =
                given()
                .when()
                .get("/index.php")
                .then()
                .contentType(ContentType.HTML)
                .assertThat()
                .statusCode(200)

                .extract().response();
        // TODO: compare the <title>Ten10 Store</title>

        // 3. Print out the response body as a string
        System.out.println("StatusCode was 200");
        System.out.println(response.asString());
    }
}
