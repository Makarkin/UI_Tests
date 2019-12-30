package restAssuredDemo;

import org.openqa.selenium.remote.Response;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import restAssuredDemo.deserialization.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;
import static restAssuredDemo.UserPropertyHandler.*;

public class RestAssuredDemoTests {

    @Test
    public void testGreetingsAPI() {
        given()
                .when()
                .get("http://localhost:8083/users")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"));

    }

    @Test
    public void testGreetingsAPIwithParameters() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        assertTrue(
                given()
                        .when()
                        .get(getURIfromProperties() + "/admin")
                        .as(User.class)
                        .equals(User.getUserFromProperties()));
    }
}
