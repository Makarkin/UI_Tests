package seleniumActionDemoPackage.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import reporting.MyLogger;

/**
 * Created by Sergey_Poritskiy on 6/15/2017.
 */
public class ResizablePage extends AbstractPage {
    private static final By SIZE_HANDLE_LOCATOR = By.cssSelector(".ui-resizable-se");

    @Step("Resize square")
    public ResizablePage resizeSquare() {
        browser.switchToFrame("0");
        browser.resize(SIZE_HANDLE_LOCATOR, 10, 10);
        MyLogger.info("Successfully resized a square!");
        return this;
    }
}
