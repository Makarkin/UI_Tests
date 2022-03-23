package defaultSeleniumTests;


import seleniumDemo.browser.Browser;
import seleniumDemo.pages.AfterLoginPage;
import seleniumDemo.pages.LoginPage;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import reporting.MyLogger;

public class ActionsDemoTests {
    private static String login;
    private static String password;
    private static Assertion assertion;
    
    @BeforeMethod(description = "Preconditions")
    public void init(){
        assertion = new Assertion();
        MyLogger.info("Init browser");
        Browser.getInstance();
    }

    @Test(description = "Successful login test")
    public void successLoginTest() {
        login = getParameterFromTestConfig("validLogin");
        password = getParameterFromTestConfig("validPassword");
        AfterLoginPage afterLoginPage = new LoginPage(Browser.getInstance()).openLoginPage().enterCredentials(login, password);
        String expectedEmployeeName = "Фомина Елена Сергеевна";
        assertion.assertEquals(afterLoginPage.getLoggedInEmployeeName().trim(), expectedEmployeeName, 
                "Validate successfully logged user");
    }

    @Test(description = "Invalid user credentials test")
    public void failedLoginTest() {
        String expectedDialogMessage = "Неверные данные для авторизации.";
        LoginPage loginPage = new LoginPage(Browser.getInstance()).openLoginPage();
        loginPage.clickSubmit();
        assertion.assertEquals(loginPage.getDialogMessage(), expectedDialogMessage, "Check impossible authorization with empty credentials");
        login = getParameterFromTestConfig("validLogin");
        password = getParameterFromTestConfig("validPassword");
        loginPage.enterLogin(login).clickSubmit();
        assertion.assertEquals(loginPage.getDialogMessage(), expectedDialogMessage, "Check impossible authorization with only login");
        loginPage.enterLogin("").enterPassword(password).clickSubmit();
        assertion.assertEquals(loginPage.getDialogMessage(), expectedDialogMessage, "Check impossible authorization with only password");
        login = getParameterFromTestConfig("invalidLogin");
        password = getParameterFromTestConfig("invalidPassword");
        loginPage.enterLogin(login).enterPassword(password).clickSubmit();
        //TODO There is some error on page - difference between dialog messages (empty field cases and invalid credentials case)
        expectedDialogMessage = "Неверные данные для авторизации";
        assertion.assertEquals(loginPage.getDialogMessage(), expectedDialogMessage, "Check impossible authorization with invalid credentials");
    }

    @Test(description = "Help options (show/hide button and password reminder) test")
    public void helpOptionsTest() {
        LoginPage loginPage = new LoginPage(Browser.getInstance()).openLoginPage();
        assertion.assertEquals(loginPage.getPasswordInputType(), "password", "Validate default password input type");
        password = getParameterFromTestConfig("validPassword");
        loginPage.enterPassword(password).clickOnShowButton();
        assertion.assertEquals(loginPage.getPasswordInputType(), "text", 
                "Validate that default password input type was changed");
        assertion.assertEquals(loginPage.moveToRemindPasswordPage().getWidgetName(), 
                "Восстановление пароля", "Validate moving to 'remind password' page");
    }

    private String getParameterFromTestConfig(String parameterName) {
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(parameterName);
    }
    
    @AfterMethod(description = "Close browser")
    public void kill(){
        MyLogger.info("Close browser");
        Browser.kill();
    }
}
