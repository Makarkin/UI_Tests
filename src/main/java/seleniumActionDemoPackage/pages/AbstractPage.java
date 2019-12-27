package seleniumActionDemoPackage.pages;

import seleniumActionDemoPackage.browser.Browser;

public class AbstractPage {
    protected Browser browser;

    protected AbstractPage() {
        this.browser = Browser.getInstance();
    }
}
