package restAssuredDemo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static restAssuredDemo.UserPropertyHandler.*;

public class RestAssuredDemoTests {

    @Test
    public void testGreetingsAPI() {
        given()
                .when()
                .get("http://localhost:8083/greeting")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .assertThat()
                .body("greeting", equalTo("Hello, User!"));

    }

    @Test
    public void testGreetingsAPIwithParameters() {
        given()
                .when()
                .get(getURIfromProperties())
                .then()
                .statusCode(200)
                .assertThat()
                //.body("user", hasItems("name","secondName","coinCount"))
                .body("user", hasKey("name"))
                .body("greeting", equalTo("Hello, Stas!"))
                .body("user.name", equalTo("Stas"), "user.secondName", equalTo("Makarkin"));
    }
}
