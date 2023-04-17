import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SignInPage {
    @Test
    public void incorrectUser() {
        //1.Set the base URI
        RestAssured.baseURI = "http://3.11.77.136";
        //2.Send an HTTP GET request to /index.php and extract the response
        Response response =
                given().queryParam("controller","authentication")
                        .queryParam("back","my-account")
                        .contentType("multipart/form-data")
                        .multiPart("back", "my-account")
                        .multiPart("email", "notuser@gfdgd.com")
                        .multiPart("password", "jdiasjds")
                        .multiPart("submitLogin", "1")
                        .when()
                        .post("/index.php")
                        .then()
                        .contentType(ContentType.HTML)
                        .assertThat()
                        .statusCode(200)
                        .body("html.body.main.section.div.div.section.section.section.div.ul.li", equalTo("Authentication failed."))
                        .extract().response();


        Document doc = Jsoup.parse(response.asString());
        Element link = doc.select("li.alert.alert-danger").first();
    }
}
