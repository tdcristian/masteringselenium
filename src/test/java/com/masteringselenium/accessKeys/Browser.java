package com.masteringselenium.accessKeys;

import org.openqa.selenium.remote.BrowserType;

public enum Browser {
    
    FIREFOX(BrowserType.FIREFOX),
    GOOGLECHROME(BrowserType.CHROME);
    
    private String type;

    Browser(String type) {
        this.type = type;
    }
    
    public static Browser getBrowserType(String browserName) {
        for (Browser browser:
             Browser.values()) {
            if (browserName.toLowerCase().contains(browser.type)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("Unrecognized browser name '"+browserName+"'");
    }
}
