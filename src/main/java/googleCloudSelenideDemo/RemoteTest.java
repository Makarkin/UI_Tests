package googleCloudSelenideDemo;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteTest {

    private WebDriver startUp() {
        WebDriver driver = null;
        String baseUrl = "https://cloud.google.com/";
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.WINDOWS);
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

    @Test
    public void googleCloudTest() {
        WebDriver driver = startUp();
        driver.navigate().to("https://cloud.google.com/pricing/");
        System.out.println("Remote test1 running");
        long id = Thread.currentThread().getId();
        System.out.println("Third test, first class. Thread id is: " + id);
        tearDown(driver);
    }

    private void tearDown(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
