import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HomePage {
    private String CLASSIC_URL = "http://3.11.77.136";
    @Test
    public void validateStatusCode200() {
                // 1. Set the base URI
        RestAssured.baseURI = CLASSIC_URL;


                // 2. Send an HTTP GET request to /index.php and extract the response
        Response response =
                given()
                .when()
                .get("/index.php")
                .then()
                    .contentType(ContentType.HTML)
                    .assertThat()
                    .statusCode(200)
                        // 3. Create an assertion using .body() to compare the text inside of a tag
                    .body("html.head.title", equalTo("Ten10 Store"))
                        .log().all()
                    .extract().response()
                            ;}
    @Test
    public void validatingContentType(){
       // String contentType = response.getContentType(); //check the scope of reponse
//        System.out.println("Content-Type of response is : "+contentType);
        System.out.println("contentType will go here");

    }
    @Test
    public void validatingHeadTitle(){
        System.out.println("head title will go here");
    }



    @Test
    public void locatedInTheHomePage(){
        System.out.println("We are in the home page"); //done
    }

    @Test
    public void testStatusCode404(){

        RestAssured.baseURI= CLASSIC_URL;


        Response response=
                given()
                        .when()
                        .get("/jfjufhdgb%20gvdm")
                        .then()
                        .contentType(ContentType.HTML)
                        .assertThat()
                        .statusCode(404)
                        .extract().response();

        System.out.println("StatusCodewas404");

    }
}
