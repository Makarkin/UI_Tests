package seleniumActionDemoPackage;

import seleniumActionDemoPackage.browser.Browser;
import org.testng.Assert;
import org.testng.annotations.*;
import seleniumActionDemoPackage.pages.DroppablePage;
import seleniumActionDemoPackage.pages.MainPage;
import seleniumActionDemoPackage.pages.ResizablePage;
import seleniumActionDemoPackage.pages.SelectablePage;
import reporting.MyLogger;

public class ActionsDemoTests {

    @BeforeTest(description = "init browser")
    public void init(){
        MyLogger.info("Init browser");
        Browser.getInstance();
    }

    @Test(description = "Drag-n-drop test")
    public void dragNDropDemo() {
        DroppablePage droppablePage = new MainPage().open().openDroppableLink();
        droppablePage.dragNDropSquare();
    }

    @Test(description = "Resize test")
    public void resizeDemo() {
        ResizablePage resizablePage = new MainPage().open().openResizableLink();
        resizablePage.resizeSquare();
    }

    @Test(description = "Selecting test")
    public void selectingDemo() {
        SelectablePage selectablePage = new MainPage().open().openSelectableLink();
        selectablePage.selectItems(5);
    }

//    @Test(description = "Broken test")
//    public void brokenTest() {
//        DroppablePage droppablePage = new MainPage().open().openDroppableLink();
//        droppablePage.dragNDropWrongSquare();
//        Assert.assertTrue(droppablePage.squareIsOnThePlace());
//    }

    @AfterTest(description = "close browser")
    public void kill(){
        MyLogger.info("Close browser");
        Browser.kill();
    }
}
