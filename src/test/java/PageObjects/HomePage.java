package PageObjects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class HomePage extends BasePage {
    public void getHomePage() {
        Response homePageResponse = getRequest("/index.php");
        Assert.assertEquals(200, homePageResponse.getStatusCode());
        Assert.assertEquals("Ten10 Store", getElementText(homePageResponse, "title"));
    }

    public void testStatusCode404() {
        Response homePageResponse = getRequest("/jfjufhdgb%20gvdm");
        Assert.assertEquals(404, homePageResponse.getStatusCode());

    }
}
