package defaultSeleniumTests;

import seleniumActionDemoPackage.browser.Browser;
import org.testng.annotations.*;
import seleniumActionDemoPackage.pages.DroppablePage;
import seleniumActionDemoPackage.pages.MainPage;
import seleniumActionDemoPackage.pages.ResizablePage;
import seleniumActionDemoPackage.pages.SelectablePage;
import reporting.MyLogger;

import java.io.IOException;

public class ActionsDemoTests {

    @BeforeTest(description = "init browser")
    public void init(){
        MyLogger.info("Init browser");
        Browser.getInstance();
    }

    @Test(description = "Drag-n-drop test")
    public void dragNDropDemo() throws IOException {
        DroppablePage droppablePage = new MainPage().open().openDroppableLink();
        droppablePage.dragNDropSquare();
    }

    @Test(description = "Resize test")
    public void resizeDemo() throws IOException {
        ResizablePage resizablePage = new MainPage().open().openResizableLink();
        resizablePage.resizeSquare();
    }

    @Test(description = "Selecting test")
    public void selectingDemo() throws IOException {
        SelectablePage selectablePage = new MainPage().open().openSelectableLink();
        selectablePage.selectItems(5);
    }

    @AfterTest(description = "close browser")
    public void kill(){
        MyLogger.info("Close browser");
        Browser.kill();
    }
}
