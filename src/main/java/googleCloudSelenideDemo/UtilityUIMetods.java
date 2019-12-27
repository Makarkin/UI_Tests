package googleCloudSelenideDemo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import reporting.MyLogger;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class UtilityUIMetods {

    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public static void clickOnLink(String elementName) {
        String link = getWebDriver().getCurrentUrl();
        Allure.addAttachment("Reference: ", "text/plain", link);
        String xpath = String.format(".//a[contains(text(), '%s')]", elementName);
        SelenideElement element = $(By.xpath(xpath));
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        element.click();
        MyLogger.info("Clicking element '" + element.getText() + "' (Located: " + xpath + ")");
        takeScreenshot();
    }

    public static void clickOnButton(String elementName) {
        String xpath = String.format(".//button[contains(text(), '%s') and not(@disabled)]", elementName);
        SelenideElement element = $(By.xpath(xpath));
        highlightElement(element);
        element.click();
        MyLogger.info("Clicking element '" + elementName + "' (Located: " + xpath + ")");
        takeScreenshot();
    }

    public static void navigateToTab(String tabName) {
        Selenide.switchTo().frame(0);
        String xpath = String.format(".//div[@title='%s' and parent::md-tab-item]", tabName);
        SelenideElement element = $(By.xpath(xpath));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getWebDriver();
        highlightElement(element);
        javascriptExecutor.executeScript("arguments[0].click();", element);
        takeScreenshot();
        unHighlightElement(element);
    }

    public static void setValueToInputField(String elementName, String value) {
        String xpath = String.format(".//label[contains(text(), '%s')]/following-sibling::input", elementName);
        SelenideElement element = $(By.xpath(xpath));
        $(By.xpath(xpath)).setValue(value);
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        MyLogger.info("Set value '" + value + "' to input field '" + elementName + "' (Located: " + xpath + ")");
    }

    public static void selectValueInSelectorWithSpecifiedName(String selectorName, String value) {
        String xpath = String.format(".//span[@class='md-select-icon' and ancestor::md-select[preceding-sibling::label[contains(text(), '%s')]]]", selectorName);
        $(By.xpath(xpath)).click();
        xpath = String.format(".//div[contains(text(), '%s') and parent::md-option]", value);
        $$(By.xpath(xpath)).filterBy(Condition.visible).get(0).click();
        MyLogger.info("Select value '" + value + "' in selector '" + selectorName + "' (Located: " + xpath + ")");
        takeScreenshot();
    }

    private static void highlightElement(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].style.border='5px solid green'", element);
    }

    private static void unHighlightElement(SelenideElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].style.border='0px'", element);
    }

    private static void takeScreenshot() {
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
