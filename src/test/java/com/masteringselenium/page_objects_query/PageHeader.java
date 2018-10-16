package com.masteringselenium.page_objects_query;

import org.openqa.selenium.By;

import com.lazerycode.selenium.util.Query;

public class PageHeader extends BasePage {

    private Query servicesLink = new Query(By.cssSelector(".nav li:nth-child(1) > a"), driver);

    private Query contactLink = new Query(By.cssSelector(".nav li:nth-child(2) > a"), driver);

    public void goToTheServicesPage() {
        servicesLink.findWebElement().click();
    }

    public void goToTheContactPage() {
        contactLink.findWebElement().click();
    }
}
