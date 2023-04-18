package TestSuite;

import PageObjects.HomePage;
import PageObjects.SignInPage;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

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
        homePage.testStatusCode404();
    }

    @Test
    public void incorrectUser() {
        signInPage.incorrectUser();
    }
    @Test
    public void correctUser() {
        homePage.getHomePage();
        signInPage.correctUser();
    }
}
