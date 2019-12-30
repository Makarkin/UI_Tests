package restAssuredDemo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UserPropertyHandler {
    private static List<String> PROPERTIES = null;

    static {
        try {
            PROPERTIES = Files.readAllLines(Paths.get("src/main/resources/connection.properties"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getConnectionProperty(String propertyName) {
        for (String property : PROPERTIES) {
            if (property.contains(propertyName)) {
                return property.split("=")[1];
            }
        }

        return null;
    }

    public static List<String> getProperties() {
        return PROPERTIES;
    }

    public static String getURIfromProperties() {
        StringBuilder url = new StringBuilder(getConnectionProperty("url"));
        String[] propertyNameAndValue;
        List<String> properties = getProperties();
        String divider = "/";
        for (String property : properties) {
            if (property.contains("parameter")) {
                propertyNameAndValue = property.split("=");
                url.append(divider + property.split("=")[0].split("\\.")[1] + "=" + propertyNameAndValue[1]);
            }
        }

        return url.toString();
    }
}
