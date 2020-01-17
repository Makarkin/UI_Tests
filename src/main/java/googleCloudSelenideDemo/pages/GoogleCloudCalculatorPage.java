package googleCloudSelenideDemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static googleCloudSelenideDemo.UtilityUIMetods.*;

public class GoogleCloudCalculatorPage extends Page {

    @Step("Navigate to \"Compute Input\" tab")
    public GoogleCloudCalculatorPage navigateToTab(String tabName) {
        Selenide.switchTo().frame(0);
        xpath = String.format(".//div[@title='%s' and parent::md-tab-item]", tabName);
        element = $(By.xpath(xpath));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getWebDriver();
        highlightElement(element);
        javascriptExecutor.executeScript("arguments[0].click();", element);
        takeScreenshot();
        unHighlightElement(element);
        return this;
    }

    @Step("Set \"{value}\" value in \"Number of instances\" input")
    public GoogleCloudCalculatorPage setValueToInputField(String elementName, String value) {
        xpath = String.format(".//label[contains(text(), '%s')]/following-sibling::input", elementName);
        element = $(By.xpath(xpath));
        $(By.xpath(xpath)).setValue(value);
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        MyLogger.info("Set value '" + value + "' to input field '" + elementName + "' (Located: " + xpath + ")");
        return this;
    }

    @Step("Select \"{value}\" item in \"{selectorName}\" selector")
    public GoogleCloudCalculatorPage selectValueInSelectorWithSpecifiedName(String selectorName, String value) {
        xpath = String.format(".//span[@class='md-select-icon' and ancestor::md-select[preceding-sibling::label[contains(text(), '%s')]]]", selectorName);
        $(By.xpath(xpath)).scrollIntoView(false).click();
        xpath = String.format(".//div[contains(text(), '%s') and parent::md-option]", value);
        $$(By.xpath(xpath)).filterBy(Condition.visible).get(0).scrollIntoView(false).click();
        MyLogger.info("Select value '" + value + "' in selector '" + selectorName + "' (Located: " + xpath + ")");
        takeScreenshot();
        return this;
    }

    @Step("Click \"Add to Estimate\" button")
    public GoogleCloudCalculatorPage clickOnButton(String elementName) {
        xpath = String.format(".//button[contains(text(), '%s') and not(@disabled)]", elementName);
        element = $(By.xpath(xpath));
        highlightElement(element);
        element.scrollIntoView(false).click();
        MyLogger.info("Clicking element '" + elementName + "' (Located: " + xpath + ")");
        takeScreenshot();
        return this;
    }

    @Step("Check that value in \"{valueName}\" item equal \"{expectedValue}\" expected value")
    public boolean checkTheEstimateWindow(String valueName, String expectedValue) {
        String xpath = String.format(".//div[@class='md-list-item-text ng-binding' and contains(text(), '%s')]", valueName);
        return expectedValue.equalsIgnoreCase($(By.xpath(xpath)).getText().split(": ")[1]);
    }
}
