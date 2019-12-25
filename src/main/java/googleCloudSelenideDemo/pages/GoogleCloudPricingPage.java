package googleCloudSelenideDemo.pages;

import io.qameta.allure.Step;

import static googleCloudSelenideDemo.UtilityUIMetods.clickOnLink;

public class GoogleCloudPricingPage {

    @Step("Switch to calculator page")
    public GoogleCloudCalculatorPage switchToCalculatorPage() {
        clickOnLink("Calculator");
        return new GoogleCloudCalculatorPage();
    }
}
