package seleniumDemo.pages;

import seleniumDemo.browser.Browser;

public class AbstractPage {
    protected Browser browser;
    
    protected AbstractPage(Browser browser) {
        this.browser = browser;
    }
}