package restAssuredDemo;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import restAssuredDemo.deserialization.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.testng.Assert.assertTrue;
import static restAssuredDemo.TestMethodLoggingWrapper.*;

public class RestAssuredDemoTests {

    @Test
    public void testStatusAndHeaderForGetRequest() {
        ValidatableResponse response = getRequestToUser("").then();
        verifyResponseStatus(response, 200);
        verifyResponseHeader(response, "application/json");
    }

    @Test
    public void testGetExistingUser() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        assertTrue(
                getRequestToUser("admin")
                        .as(User.class)
                        .equals(User.getUserFromProperties()));
    }

    @Test
    public void testPostNewUser() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        User user = new User();
        String username = "user" + (new Random().nextInt(1000));
        user.setUsername(username);
        user.setCoinCount("2089");
        postRequestForUser(user);
        assertTrue(
                getRequestToUser(username)
                        .as(User.class)
                        .equals(user));
    }
}
