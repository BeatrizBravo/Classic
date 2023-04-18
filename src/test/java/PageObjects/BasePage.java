package PageObjects;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static io.restassured.RestAssured.given;

abstract class BasePage {
    RequestSpecification requestSpecification;

    public BasePage() {
        requestSpecification = given();
    }

    public Response getRequest(String path) {
        return requestSpecification
                .when()
                .get(path)
                .then()
                .extract().response();
    }
    public Response getRequest(String path, Map<String, String> queryParameters) {
        for (Map.Entry<String, String> entry: queryParameters.entrySet()) {
            requestSpecification
                    .queryParam(String.valueOf(entry.getKey()), entry.getValue());
        }
        return getRequest(path);
    }
    public String getElementText(Response response, String cssQuery) {
        Document doc = Jsoup.parse(response.asString());
        Element link = doc.select(cssQuery).first();
        return Objects.requireNonNull(link).text();
    }
}
