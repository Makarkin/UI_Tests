package googleCloudSelenideDemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import googleCloudSelenideDemo.UtilityUIMetods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static googleCloudSelenideDemo.UtilityUIMetods.*;

public class GoogleCloudCalculatorPage extends Page {

    private JavascriptExecutor javascriptExecutor;

    @Step("Navigate to \"{tabName}\" tab")
    public GoogleCloudCalculatorPage navigateToTab(String tabName) {
        UtilityUIMetods.waitUntilPageIsCompletelyLoaded(getWebDriver(), 5);
        Selenide.switchTo().frame(getWebDriver().findElement(By.xpath(".//iframe")))
                .switchTo().frame(getWebDriver().findElement(By.xpath(".//iframe")));
        xpath = String.format(".//md-tab-item[descendant::div[@title='%s']]", tabName);
        element = $x(xpath).waitUntil(Condition.exist, 10000);
        clickOnInnerElement(javascriptExecutor, element);
        takeScreenshot();
        unHighlightElement(element);
        return this;
    }

    @Step("Set \"{insertionValue}\" value in \"Number of instances\" input")
    public GoogleCloudCalculatorPage setValueToInputField(String elementName, String insertionValue) {
        xpath = String.format(".//label[contains(text(), '%s')]/following-sibling::input", elementName);
        element = $x(xpath);
        $x(xpath).setValue(insertionValue);
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        MyLogger.info("Set value '" + insertionValue + "' to input field '" + elementName + "' (Located: " + xpath + ")");
        return this;
    }

    @Step("Select \"{selectionValue}\" item in \"{selectorName}\" selector")
    public GoogleCloudCalculatorPage selectValueInSelectorWithSpecifiedName(String selectorName, String selectionValue) {
        xpath = String.format(".//span[@class='md-select-icon' and ancestor::md-select[preceding-sibling::label[contains(text(), '%s')]]]", selectorName);
        clickOnInnerElement(javascriptExecutor, $x(xpath));
        xpath = String.format(".//div[contains(text(), '%s') and parent::md-option]", selectionValue);
        clickOnInnerElement(javascriptExecutor, $$x(xpath).filterBy(Condition.visible).get(0).scrollIntoView(false));
        MyLogger.info("Select selectionValue '" + selectionValue + "' in selector '" + selectorName + "' (Located: " + xpath + ")");
        takeScreenshot();
        return this;
    }

    @Step("Click \"Add to Estimate\" button")
    public GoogleCloudCalculatorPage clickOnButton(String elementName) {
        xpath = String.format(".//button[contains(text(), '%s') and not(@disabled)]", elementName);
        element = $x(xpath);
        clickOnInnerElement(javascriptExecutor, element.scrollIntoView(false));
        MyLogger.info("Clicking element '" + elementName + "' (Located: " + xpath + ")");
        takeScreenshot();
        return this;
    }

    @Step("Check that value in \"{valueName}\" item equal \"{expectedValue}\" expected value")
    public boolean checkTheEstimateWindow(String valueName, String expectedValue) {
        String xpath = String.format(".//div[@class='md-list-item-text ng-binding' and contains(text(), '%s')]", valueName);
        return expectedValue.equalsIgnoreCase($x(xpath).getText().split(": ")[1]);
    }

    private void clickOnInnerElement(JavascriptExecutor javascriptExecutor, SelenideElement element) {
        highlightElement(element);
        javascriptExecutor = (JavascriptExecutor) getWebDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }
}