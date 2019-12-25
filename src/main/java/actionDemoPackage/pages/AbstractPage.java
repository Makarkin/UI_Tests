package actionDemoPackage.pages;

import actionDemoPackage.browser.Browser;

public class AbstractPage {
    protected Browser browser;

    protected AbstractPage() {
        this.browser = Browser.getInstance();
    }
}
