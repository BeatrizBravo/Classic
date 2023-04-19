package PageObjects;


import io.restassured.RestAssured;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
public class addPruduct {
    @Test
    public void addingPruduct() {

        String path = "/index.php";

        RestAssured.baseURI = "http://3.11.77.136";


        String token = given()
                .queryParam("id_product", "1")
                .queryParam("id_product_attribute", "1")
                .queryParam("rewrite", "faded-short-sleeves-tshirt")
                .queryParam("controller", "product")

                .when().get(path)
                .then()
                .log().body().
                extract().response().header("token");

//                .getBody().toString()

        System.out.println("my token::::::::::::::::::::::::" + token);

        Document doc = Jsoup.parse(token);
        System.out.println("PRINTING DOC*********************"+doc);

        Element text = doc.select("html> head > script").first();
        System.out.println("getting the text"+text);













/*
        Response response =
                given().
                        queryParam("controller", "cart")

//                        .contentType("text/html; charset=utf-8")
//                        .multiPart("token", "8b3ee4f1cba54a1031be9bb7879e9dad")

                        .formParam("id_product", "1")
                        .formParam("id_customization", "0")
                        .formParam("group[1]", "1")
                        .formParam("group[3]", "13")
                        .formParam("qty", "2")
                        .formParam("add", "1")
                        .formParam("action", "update")
                .when()
                        .post(path)

                .then()
//                        .log().headers()
//                        .contentType(ContentType.HTML)
//                        .assertThat()
//                        .contentType(ContentType.HTML.withCharset("utf-8")).log().all()

//                        .statusCode(302)
//                        .body("html.body.div.div.div.div.h4", equalTo("\uE876Product successfully added to your shopping cart"))

                        .extract().response();
        System.out.println("RESPONSE::"+response.asString());


//        Document doc = Jsoup.parse(response.asString());
//        Element link = doc.select("#myModalLabel").first();
//        System.out.println(link);

//        String script = given().when().post(CLASIC).htmlPath().getString("html.head.script[0].@src");
//        System.out.println("I am a " + script);
//        .body("html.head.script[0]", equalTo("Ten10 Store")).log().

    */
    }


}
