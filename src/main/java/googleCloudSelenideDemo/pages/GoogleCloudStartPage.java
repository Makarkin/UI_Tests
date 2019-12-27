package googleCloudSelenideDemo.pages;

import io.qameta.allure.Allure;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import io.qameta.allure.Step;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static googleCloudSelenideDemo.UtilityUIMetods.clickOnLink;

public class GoogleCloudStartPage {

    public GoogleCloudStartPage() {
        MyLogger.info("Going to URL: https://cloud.google.com/");
        open("https://cloud.google.com/");
    }


    @Step("Switch to product page")
    public GoogleCloudProductPage switchToProductsPage() {
        clickOnLink("See all products");
        return new GoogleCloudProductPage();
    }
}
