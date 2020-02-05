package googleCloudSelenideDemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import reporting.MyLogger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.google.common.io.Files.toByteArray;
import static com.google.common.io.Resources.getResource;
import static googleCloudSelenideDemo.UtilityUIMetods.*;

public class Page {

    protected static String xpath = null;
    protected static SelenideElement element = null;

    @Step("Switch to another page")
    public <T extends Page> T switchToPageByLink(String linkName, T page) {
        clickOnLink(linkName);
        return page;
    }

    protected static void clickOnLink(String elementName) {
        String link = getWebDriver().getCurrentUrl();
        Allure.addAttachment("Reference to page", "text/plain", link);
        xpath = String.format(".//a[contains(text(), '%s')]", elementName);
        element = $$x(xpath).filterBy(Condition.visible).get(0);
        highlightElement(element);
        takeScreenshot();
        unHighlightElement(element);
        String text = element.getText();
        element.click();
        MyLogger.info("Clicking element '" + text + "' (Located: " + xpath + ")");
        takeScreenshot();
    }

//    @Attachment(value = "HTML attachment", type = "text/html")
//    protected static byte[] clickOnLink(String elementName) {
//        String link = getWebDriver().getCurrentUrl();
//        String linkURL = null;
//        try(FileWriter writer = new FileWriter("target/test-classes/LinkAttach.html", false)) {
//            linkURL = String.format("<p><a href=\"%s\">Current page</a></p>", link);
//            writer.write(linkURL);
//            writer.flush();
//        }
//        catch(IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        xpath = String.format(".//a[contains(text(), '%s')]", elementName);
//        element = $$x(xpath).filterBy(Condition.visible).get(0);
//        highlightElement(element);
//        takeScreenshot();
//        unHighlightElement(element);
//        String text = element.getText();
//        element.click();
//        MyLogger.info("Clicking element '" + text + "' (Located: " + xpath + ")");
//        takeScreenshot();
//        try {
//        URL defaultImage = getResource("LinkAttach.html");
//        File imageFile = null;
//        imageFile = new File(defaultImage.toURI());
//        System.out.println(imageFile.getAbsoluteFile());
//        return toByteArray(imageFile);
//        } catch (URISyntaxException | IOException e) {
//            e.printStackTrace();
//        }
//        return new byte[0];
//    }
}
