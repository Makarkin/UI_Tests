package googleCloudSelenideDemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static googleCloudSelenideDemo.UtilityUIMetods.*;

public class Page {

    protected static String xpath = null;
    protected static SelenideElement element = null;

    @Step("Switch to another page")
    public <T extends Page> T switchToPageByLink(String linkName, T page) {
        clickOnLink(linkName);
        return page;
    }

    protected static void clickOnLink(String elementName) {
        String link = getWebDriver().getCurrentUrl();
        Allure.addAttachment("Reference to page", "text/plain", link);
        xpath = String.format(".//a[contains(text(), '%s')]", elementName);
        element = $(By.xpath(xpath));
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        String text = element.getText();
        element.click();
        MyLogger.info("Clicking element '" + text + "' (Located: " + xpath + ")");
        takeScreenshot();
    }
}
