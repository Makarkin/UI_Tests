package restAssuredDemo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemoTests {

    @Test
    public void testGreetingsAPI() {
        given()
                .when()
                .get("http://localhost:8080/greeting")
                .then()
                .statusCode(200)
                .assertThat()
                .body("greeting", equalTo("Hello, User!"));
    }

    @Test
    public void testGreetingsAPIwithParameters() {
        given()
                .when()
                .get("http://localhost:8080/greeting?name=Stas&secondName=Makarkin&coinCount=40")
                .then()
                .statusCode(200)
                .assertThat()
                //.body("user", hasItems("name","secondName","coinCount"))
                .body("user", hasKey("name"))
                .body("greeting", equalTo("Hello, Stas!"))
                .body("user.name", equalTo("Stas"), "user.secondName", equalTo("Makarkin"));

    }
}
