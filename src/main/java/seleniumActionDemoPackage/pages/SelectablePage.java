package seleniumActionDemoPackage.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import reporting.MyLogger;

public class SelectablePage extends AbstractPage {

    public static final By FIRST_ITEM_LOCATOR = By.xpath("//li[1]");
    public static final String LAST_ITEM_LOCATOR_PATTERN = "//li[%d]";

    @Step("Select {itemsToSelect} items")
    public void selectItems(int itemsToSelect) {
        browser.switchToFrame("0");
        By lastItemLocator = By.xpath(String.format(LAST_ITEM_LOCATOR_PATTERN, itemsToSelect));
        browser.selectItems(FIRST_ITEM_LOCATOR, lastItemLocator);
        MyLogger.info("Successfully selected " + itemsToSelect + " elements!");
    }
}
