package restAssuredDemo;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import reporting.MyLogger;
import restAssuredDemo.deserialization.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static restAssuredDemo.UserPropertyHandler.getURIfromProperties;

public class TestMethodLoggingWrapper {

    @Step("GET response from user from API endpoint")
    public static Response getRequestToUser(String userName) {
        Response response = given().when().get(getURIfromProperties() + "/" + userName);
        ResponseBody responseBody = response.getBody();
        MyLogger.info("GET response from user " + userName + ": " + responseBody.asString());
        Allure.addAttachment("Response", "text/plain", responseBody.asString());
        return response;
    }

    @Step("POST request for new user")
    public static void postRequestForUser(User newUser) {
        ResponseBody responseBody = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(newUser)
                .post((getURIfromProperties())).getBody();
        MyLogger.info("POST new user " + newUser.getUsername() + "\r\n" + responseBody.asString());
        Allure.addAttachment("Response", "text/plain", responseBody.asString());
    }

    @Step("Verify that response status is equal {status}")
    public static ValidatableResponse verifyResponseStatus(ValidatableResponse response, int status) {
        try {
            response.statusCode(status);
            MyLogger.info(String.format("Response status is equal expected status %s", status));
        } catch (AssertionError error) {
            MyLogger.error(String.format("Response status is not equal expected status %s", status));
        } finally {
            return response;
        }
    }

    @Step("Verify that response content type header is equal {contentType}")
    public static ValidatableResponse verifyResponseHeader(ValidatableResponse response, String contentType) {
        try {
            response.header("Content-Type", equalTo(contentType));
            MyLogger.info(String.format("Response Content-Type is equal expected type %s", contentType));
        } catch (AssertionError error) {
            MyLogger.error(String.format("Response Content-Type is not equal expected type %s", contentType));
        } finally {
            return response;
        }
    }
}
