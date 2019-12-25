package googleCloudSelenideDemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static googleCloudSelenideDemo.UtilityUIMetods.*;

public class GoogleCloudCalculatorPage implements IAbstractPage {

    @Step("Navigate to \"Compute Input\" tab")
    public GoogleCloudCalculatorPage navigateToComputeInputTab() {
        navigateToTab("Compute Engine");
        return this;
    }

    @Step("Set \"{value}\" value in \"Number of instances\" input")
    public GoogleCloudCalculatorPage setValueToInstanceInputField(String value) {
        setValueToInputField("Number of instances", value);
        return this;
    }

    @Step("Select \"{value}\" item in \"{selectorName}\" selector")
    public GoogleCloudCalculatorPage selectValueInSelector(String selectorName, String value) {
        selectValueInSelectorWithSpecifiedName(selectorName, value);
        return this;
    }

    @Step("Click \"Add to Estimate\" button")
    public GoogleCloudCalculatorPage addToEstimate() {
        clickOnButton("Add to Estimate");
        return this;
    }

    @Override
    @Step("Check that value in \"{valueName}\" item equal \"{expectedValue}\" expected value")
    public boolean checkTheEstimateWindow(String valueName, String expectedValue) {
        String xpath = String.format(".//div[@class='md-list-item-text ng-binding' and contains(text(), '%s')]", valueName);
        return expectedValue.equalsIgnoreCase($(By.xpath(xpath)).getText().split(": ")[1]);
    }
}
