package com.masteringselenium.page_objects_query;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.masteringselenium.DriverBase;

public abstract class BasePage {

    protected RemoteWebDriver driver;

    public BasePage() {
        driver = DriverBase.getDriver();
    }
}
