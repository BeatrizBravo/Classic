package PageObjects;


import io.restassured.http.Cookies;
import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.Objects;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class CreateAccount {


    private TestCase Assert;


    public String createAccount() {


        //2.Send an HTTP GET request to /index.php and extract the response
        Cookies signInCookies =
                given().queryParam("controller","authentication")
                        .queryParam("create_account","1")
                        .contentType("multipart/form-data")
                        .when()
                        .post("/index.php")
                        .then()
                        .contentType(ContentType.HTML)
                        .assertThat()
                        // .statusCode(302)
                        .extract().response()
                        .getDetailedCookies();


        Response response =
                given().queryParam("controller", "authentication")
                        .queryParam("create_account", "1")
                        .queryParam("back", "my-account")
                        .cookies(signInCookies)
                        .contentType("multipart/form-data")
                        .multiPart("first name", "June")
                        .multiPart("last name", "Brown")
                        .multiPart("email", "notuser@gfdgtgd.com")
                        .multiPart("password", "jdiasjfrds")
                        .multiPart("birthdate", "26/02/1992")
                        .multiPart("save", "1")
                        .when()
                        .post("/index.php")
                        .then()
                        .contentType(ContentType.HTML)
                        .statusCode(200)
                        .extract().response();




        Document doc = Jsoup.parse(response.asString());
        Element link = doc.select("a.account > span").first();
        Assert.assertEquals("june brown", Objects.requireNonNull(link).text());


        return "Account Created";
    }
}
