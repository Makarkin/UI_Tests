package restAssuredDemo;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import restAssuredDemo.deserialization.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;
import static restAssuredDemo.UserPropertyHandler.*;

public class RestAssuredDemoTests {

    @Test
    public void testStatusAndHeaderForGetRequest() {
        given()
                .when()
                .get("http://localhost:8083/users")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"));
    }

    @Test
    public void testGetExistingUser() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        assertTrue(
                given()
                        .when()
                        .get(getURIfromProperties() + "/admin")
                        .as(User.class)
                        .equals(User.getUserFromProperties()));
    }

    @Test
    public void testPostNewUser() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        User user = new User();
        String username = "user" + (new Random().nextInt(1000));
        user.setUsername(username);
        user.setCoinCount("2089");
        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(user)
                .post((getURIfromProperties()));
        assertTrue(
                given()
                        .when()
                        .get(getURIfromProperties() + "/" + username)
                        .as(User.class)
                        .equals(user));
    }
}
