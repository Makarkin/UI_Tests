package restTests;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import restAssuredDemo.deserialization.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static restAssuredDemo.TestMethodLoggingWrapper.*;

public class RestAssuredDemoTests {

    @Test(description = "Check default response status and header.")
    public void testStatusAndHeaderForGetRequest() {
        ValidatableResponse response = getRequestToUser("").then();
        verifyResponseStatus(response, 200);
        verifyResponseHeader(response, "application/json");
    }

    @Test(description = "Check search response for \"admin\" user.")
    public void testGetExistingUser() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        assertEquals(User.getUserFromProperties(), getRequestToUser("admin")
                .as(User.class));
    }

    @Test(description = "Create and check search response for new user.")
    public void testPostNewUser() {
        User user = new User();
        String username = "user" + (new Random().nextInt(1000));
        user.setUsername(username);
        user.setCoinCount("67");
        postRequestForUser(user);
        assertEquals(user, getRequestToUser(username)
                .as(User.class));
    }
}
