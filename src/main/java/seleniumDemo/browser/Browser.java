package seleniumDemo.browser;

import io.qameta.allure.Attachment;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import reporting.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 30;
    private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 30;
    private static final int WAIT_ELEMENT_TIMEOUT = 30;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    private WebDriver driver;
    private static Browser instance = null;

    private Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static Browser getInstance() {
        if (instance != null) {
            return instance;
        }

        String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        switch (browserName) {
            case "mozilla":
                return instance = initGeckoDriver();
            default:
                return instance = initChromeDriver();
        }
    }

    private static Browser initChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        setUpOptions(driver);
        return new Browser(driver);
    }

    private static Browser initGeckoDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        setUpOptions(driver);
        return new Browser(driver);
    }

    private static void setUpOptions(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    private void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
    }

    private void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    public void open(String url) {
        MyLogger.info("Going to URL: " + url);
        driver.get(url);
    }

    public void click(final By locator) {
        waitForElementVisible(locator);
        MyLogger.info("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
        highlightElement(locator);
        takeScreenshot();
        unHighlightElement(locator);
        driver.findElement(locator).click();
    }

    public void type(final By locator, String text) {
        waitForElementVisible(locator);
        highlightElement(locator);
        MyLogger.info("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
        driver.findElement(locator).sendKeys(text);
        takeScreenshot();
        unHighlightElement(locator);
    }

    public String read(final By locator) {
        waitForElementVisible(locator);
        highlightElement(locator);
        MyLogger.info("Reading text: " + driver.findElement(locator).getText());
        takeScreenshot();
        unHighlightElement(locator);
        return driver.findElement(locator).getText();
    }

    private void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            String scrPath = screenshotName + ".jpg";
            File copy = new File(scrPath);
            FileUtils.copyFile(screenshot, copy);
            captureScreenshot(this.driver);
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] captureScreenshot(WebDriver driver) {
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public static void kill() {
        if (instance != null) {
            instance.driver.quit();
            instance = null;
        }
    }
}