package actionDemoPackage;

import actionDemoPackage.browser.Browser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import actionDemoPackage.pages.DroppablePage;
import actionDemoPackage.pages.MainPage;
import actionDemoPackage.pages.ResizablePage;
import actionDemoPackage.pages.SelectablePage;
import reporting.MyLogger;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

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

    @Test(description = "Broken test")
    public void brokenTest() {
        DroppablePage droppablePage = new MainPage().open().openDroppableLink();
        droppablePage.dragNDropWrongSquare();
        Assert.assertTrue(droppablePage.squareIsOnThePlace());
    }

    @AfterTest(description = "close browser")
    public void kill(){
        MyLogger.info("Close browser");
        Browser.kill();
    }
}
