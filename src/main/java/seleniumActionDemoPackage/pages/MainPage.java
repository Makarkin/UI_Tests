package seleniumActionDemoPackage.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;

public class MainPage extends AbstractPage {
    private static final String MAIN_URL = "https://jqueryui.com";
    private static final By DROPPABLE_LINK_LOCATOR = By.cssSelector("a[href$='droppable/']");
    private static final By RESIZABLE_LINK_LOCATOR = By.cssSelector("a[href$='resizable/']");
    private static final By SELECTABLE_LINK_LOCATOR = By.cssSelector("a[href$='selectable/']");

    @Step("Open main page")
    public MainPage open() throws IOException {
        FileInputStream inFile = new FileInputStream("jettyStub.xml");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);
        System.out.println(text);
        browser.open(MAIN_URL);
        return this;
    }

    @Step("Open page with droppable objects")
    public DroppablePage openDroppableLink() {
        browser.click(DROPPABLE_LINK_LOCATOR);
        return new DroppablePage();
    }

    @Step("Open page with resizable objects")
    public ResizablePage openResizableLink() {
        browser.click(RESIZABLE_LINK_LOCATOR);
        return new ResizablePage();
    }

    @Step("Open page with selectable objects")
    public SelectablePage openSelectableLink() {
        browser.click(SELECTABLE_LINK_LOCATOR);
        return new SelectablePage();
    }
}
