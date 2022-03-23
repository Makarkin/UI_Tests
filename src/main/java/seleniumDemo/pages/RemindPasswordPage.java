package seleniumDemo.pages;

import org.openqa.selenium.By;

import seleniumDemo.browser.Browser;

public class RemindPasswordPage extends AbstractPage {
    private static final By REMIND_PASSWORD_WIDGET_LOCATOR = By.xpath("//div[@class='info-title']");

    public RemindPasswordPage(Browser browser) {
        super(browser);
    }

    public String getWidgetName() {
        return browser.read(REMIND_PASSWORD_WIDGET_LOCATOR);
    }
}