package com.masteringselenium.query_page_objects;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.masteringselenium.DriverBase;

public abstract class BasePage {

    protected RemoteWebDriver driver;

    public BasePage() {
        driver = DriverBase.getDriver();
    }
}
