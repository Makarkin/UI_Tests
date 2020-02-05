package googleCloudTests;

import com.codeborne.selenide.Configuration;
import googleCloudSelenideDemo.businessObjects.AssertionOfVariableAndValue;
import googleCloudSelenideDemo.pages.GoogleCloudCalculatorPage;
import googleCloudSelenideDemo.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static googleCloudSelenideDemo.UtilityBusinessMethods.checkTheEstimateResultsOnPage;
import static org.testng.Assert.assertTrue;

public class RemoteTest {

    private WebDriver startUp() {
        WebDriver driver = null;
        String baseUrl = "https://cloud.google.com/";
        Configuration.startMaximized = true;
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setBrowserName("chrome");
        try {
             driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert driver != null;
        driver.manage().window().maximize();
        driver.get(baseUrl);
        return  driver;
    }

    @Test(description = "Test for google.cloud.com price calculator on remote machine.")
    public void googleCloudTest() {
        WebDriver driver = startUp();
        setWebDriver(driver);
        driver.navigate().to("https://cloud.google.com/");
        GoogleCloudCalculatorPage page = new Page()
                .switchToPageByLink("Products", new Page())
                .switchToPageByLink("See all products", new Page())
                .switchToPageByLink("See pricing", new Page())
                .switchToPageByLink("Calculator", new GoogleCloudCalculatorPage())
                .navigateToTab("Compute Engine")
                .setValueToInputField("Number of instances","4")
                .selectValueInSelectorWithSpecifiedName("Operating System / Software",
                        "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectValueInSelectorWithSpecifiedName("Machine Class", "Regular")
                .selectValueInSelectorWithSpecifiedName("Machine type", "n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .selectValueInSelectorWithSpecifiedName("Local SSD", "2x375 GB")
                .selectValueInSelectorWithSpecifiedName("Datacenter location", "Frankfurt (europe-west3)")
                .selectValueInSelectorWithSpecifiedName("Committed usage", "1 Year")
                .clickOnButton("Add to Estimate");
        assertTrue(checkTheEstimateResultsOnPage(new AssertionOfVariableAndValue(), page));
        tearDown(driver);
    }

    private void tearDown(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
