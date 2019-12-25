package googleCloudSelenideDemo.pages;

import io.qameta.allure.Step;

import static googleCloudSelenideDemo.UtilityUIMetods.clickOnLink;

public class GoogleCloudProductPage {

    @Step("Switch to pricing page")
    public GoogleCloudPricingPage switchToPricingPagePage() {
        clickOnLink("See pricing");
        return new GoogleCloudPricingPage();
    }
}
