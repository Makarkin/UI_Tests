package seleniumDemo.pages;

import io.qameta.allure.Step;

import org.openqa.selenium.By;

import seleniumDemo.browser.Browser;

public class LoginPage extends AbstractPage {
    private static final String MAIN_URL = "https://lmslite47vr.demo.mirapolis.ru/mira";
    private static final By LOGIN_INPUT_LOCATOR = By.xpath("//input[@name='user']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
    private static final By SHOW_PASSWORD_BUTTON_LOCATOR = By.xpath("//button[@id='show_password']");
    private static final By FORGOT_PASSWORD_BUTTON_LOCATOR = By.xpath("//a[@class='mira-default-login-page-link']/div");
    private static final By ENTER_CREDENTIALS_BUTTON_LOCATOR = By.xpath("//button[@id='button_submit_login_form']");

    public LoginPage(Browser browser) {
        super(browser);
    }
    
    @Step("Open login page")
    public LoginPage openLoginPage() {
        browser.open(MAIN_URL);
        return this;
    }

    @Step("Enter user credentials")
    public AfterLoginPage enterCredentials(String login, String password) {
        return enterLogin(login).enterPassword(password).clickSubmit();
    }

    @Step("Enter user login")
    public LoginPage enterLogin(String login) {
        browser.type(LOGIN_INPUT_LOCATOR, login);
        return this;
    }
    
    @Step("Enter user password")
    public LoginPage enterPassword(String password) {
        browser.type(PASSWORD_INPUT_LOCATOR, password);
        return this;
    }

    @Step("Enter user login")
    public AfterLoginPage clickSubmit() {
        browser.click(ENTER_CREDENTIALS_BUTTON_LOCATOR);
        return new AfterLoginPage(browser);
    }
    
    @Step("Click on 'show/hide password' button")
    public LoginPage clickOnShowButton() {
        browser.click(SHOW_PASSWORD_BUTTON_LOCATOR);
        return this;
    }
    
    @Step("Click on 'Forgot password?' button")
    public RemindPasswordPage moveToRemindPasswordPage() {
        browser.click(FORGOT_PASSWORD_BUTTON_LOCATOR);
        return new RemindPasswordPage(browser);
    }
    
    public String getPasswordInputType() {
        return browser.getDriver().findElement(PASSWORD_INPUT_LOCATOR).getAttribute("type");
    }

    public String getDialogMessage() {
        String result = browser.getDriver().switchTo().alert().getText();
        browser.getDriver().switchTo().alert().accept();
        return result;
    }
}