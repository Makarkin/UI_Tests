package seleniumDemo.pages;

import org.openqa.selenium.By;

import seleniumDemo.browser.Browser;

public class AfterLoginPage extends AbstractPage {
    private static final By EMPLOYEE_NAME_LOCATOR = By.xpath("//div[@class='avatar-full-name']");

    public AfterLoginPage(Browser browser) {
        super(browser);
    }

    public String getLoggedInEmployeeName() {
        return browser.read(EMPLOYEE_NAME_LOCATOR);
    }
}