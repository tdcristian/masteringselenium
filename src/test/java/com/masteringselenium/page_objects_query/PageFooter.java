package com.masteringselenium.page_objects_query;

import org.openqa.selenium.By;

import com.lazerycode.selenium.util.Query;

public class PageFooter extends BasePage {

    private Query aboutUsLink = new Query(By.cssSelector(".left-footer > a"), driver);

    public void goToTheAboutUsPage() {
        aboutUsLink.findWebElement().click();
    }

    public AboutPage goToTheAboutUsPageSecond() {
        aboutUsLink.findWebElement().click();
        return new AboutPage();
    }
}
