package TestSuite;

import PageObjects.HomePage;
import PageObjects.SignInPage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.CoreMatchers.equalTo;

public class Ten10Store {

    HomePage homePage;
    SignInPage signInPage;



    @Before
    public  void setup() {
        // Setting BaseURI once
        RestAssured.baseURI = "http://3.11.77.136";
        homePage = new HomePage();
        signInPage = new SignInPage();

    }
     @Test
     public void setHomePage(){
        homePage.getHomePage();
        homePage.testStatusCode400();
    }

    @Test
    public void incorrectUser() {
        signInPage.incorrectUser();
    }
}
