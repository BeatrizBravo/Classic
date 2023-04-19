package PageObjects;

import io.restassured.http.Cookies;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SignInPage extends BasePage {

    public void incorrectUser() {


        Response response =
                given().queryParam("controller", "authentication")
                        .queryParam("back", "my-account")
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
                        .extract().response();


        Document doc = Jsoup.parse(response.asString());
        Element link = doc.select("li.alert.alert-danger").first();
        Assert.assertEquals("Authentication failed.", Objects.requireNonNull(link).text());

    }

    public void correctUser() {

        RestAssured.baseURI = "http://3.11.77.136";

        Cookies signInCookies =
                given().queryParam("controller", "authentication")

                        .queryParam("back", "my-account")
                        .contentType("multipart/form-data")
                        .multiPart("back", "my-account")
                        .multiPart("email", "user@user.co.uk")
                        .multiPart("password", "password")
                        .multiPart("submitLogin", "1")
                        .when()
                        .post("/index.php")
                        .then()
                        .contentType(ContentType.HTML)
                        .assertThat()
                        .statusCode(302)
                        .extract().response()
                        .getDetailedCookies();
        setSessionCookies(signInCookies);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("controller", "my-account");

        Response accountPageResponse = getRequest("/index.php", queryParams);

        Document doc = Jsoup.parse(accountPageResponse.asString());
        Element link = doc.select("a.account > span").first();
        Assert.assertEquals("asd asd", Objects.requireNonNull(link).text());
    }
}
