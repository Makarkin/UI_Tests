package reporting;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {

    private static final Logger LOGGER = LogManager.getLogger(MyLogger.class);

    @Step("Log successful test step")
    public static void info(String message) {
        LOGGER.info(message);
    }

    @Step("Log test step with error")
    public static void error(String message) {
        LOGGER.error(message);
    }
}
