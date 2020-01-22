package googleCloudSelenideDemo;

import com.codeborne.selenide.Configuration;
import googleCloudSelenideDemo.businessObjects.AssertionOfVariableAndValue;
import googleCloudSelenideDemo.pages.*;
import org.testng.TestNG;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.open;
import static googleCloudSelenideDemo.UtilityBusinessMethods.checkTheEstimateResultsOnPage;
import static org.testng.Assert.assertTrue;

public class GoogleCloudDemoTests {

    @BeforeTest
    private void config() {
        //XmlSuite xmlSuite = new XmlSuite();
        //xmlSuite.setParallel(XmlSuite.ParallelMode.METHODS);
        //xmlSuite.setThreadCount(Integer.parseInt(System.getenv("numberOfThreads")));
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        Configuration.proxyPort = 8084;
        MyLogger.info("Going to URL: https://cloud.google.com/");
    }

    @Test
    public void googleCloudTest() {
        open("https://cloud.google.com/");
        GoogleCloudCalculatorPage page = new Page()
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
    }

    @Test
    public void googleCloudTest1() {
        open("https://cloud.google.com/");
        GoogleCloudCalculatorPage page = new Page()
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
    }
}
