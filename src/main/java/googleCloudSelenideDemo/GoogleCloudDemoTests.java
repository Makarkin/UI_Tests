package googleCloudSelenideDemo;

import com.codeborne.selenide.Configuration;
import googleCloudSelenideDemo.businessObjects.AssertionOfVariableAndValue;
import googleCloudSelenideDemo.pages.GoogleCloudCalculatorPage;
import googleCloudSelenideDemo.pages.GoogleCloudStartPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static googleCloudSelenideDemo.UtilityBusinessMethods.checkTheEstimateResultsOnPage;
import static org.testng.Assert.assertTrue;

public class GoogleCloudDemoTests {

    @BeforeTest
    private void config() {
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        Configuration.browserVersion = "79.0.3945.88";
    }

    @Test
    public void googleCloudTest() {
        GoogleCloudCalculatorPage page = new GoogleCloudStartPage()
                .switchToProductsPage()
                .switchToPricingPagePage()
                .switchToCalculatorPage()
                .navigateToComputeInputTab()
                .setValueToInstanceInputField("4")
                .selectValueInSelector("Operating System / Software",
                        "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectValueInSelector("Machine Class", "Regular")
                .selectValueInSelector("Machine type", "n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .selectValueInSelector("Local SSD", "2x375 GB")
                .selectValueInSelector("Datacenter location", "Frankfurt (europe-west3)")
                .selectValueInSelector("Committed usage", "1 Year")
                .addToEstimate();

        assertTrue(checkTheEstimateResultsOnPage(new AssertionOfVariableAndValue(), page));
    }
}
