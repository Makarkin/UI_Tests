package restAssuredDemo.deserialization;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import reporting.MyLogger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class User {

    private String username;

    private int coinCount;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = Integer.parseInt(coinCount);
    }

    public static User getUserFromProperties() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        String setterNameTemplate = "set%s";
        String[] propertyNameAndValue;
        List<String> properties = Files.readAllLines(Paths.get("src/main/resources/user.properties"), StandardCharsets.UTF_8);
        for (String nameAndValue : properties) {
            propertyNameAndValue = nameAndValue.split("=");
            User.class.getMethod(String.format(setterNameTemplate, propertyNameAndValue[0]), String.class).invoke(user, propertyNameAndValue[1]);
        }

        return user;
    }


    @Override
    @Step("Verify that user instance equals another user instance")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        boolean result = coinCount == user.coinCount && username.equals(user.username);
        MyLogger.info(String.format("User instance \"%s\" is equal user instance \"%s\": %s", this.getUsername(), user.getUsername(), result));
        Allure.addAttachment("Result", "text/plain", String.format("User instance \"%s\" is equal user instance \"%s\": %s", this.getUsername(), user.getUsername(), result));
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, coinCount);
    }
}