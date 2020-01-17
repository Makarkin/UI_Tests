package googleCloudSelenideDemo;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import reporting.MyLogger;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UtilityUIMetods {

    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public static void highlightElement(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].style.border='5px solid green'", element);
    }

    public static void unHighlightElement(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].style.border='0px'", element);
    }

    public static void takeScreenshot() {
        File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            String scrPath = screenshotName + ".jpg";
            File copy = new File(scrPath);
            FileUtils.copyFile(screenshot, copy);
            captureScreenshot(getWebDriver());
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] captureScreenshot(WebDriver driver) {
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }
}
