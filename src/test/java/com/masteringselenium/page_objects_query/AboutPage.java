package com.masteringselenium.page_objects_query;

import org.openqa.selenium.By;

import com.lazerycode.selenium.util.Query;

public class AboutPage extends BasePage {

    private Query heading = new Query(By.cssSelector("h1"),
            driver);

    private Query aboutUsText = new Query(By.cssSelector
            (".col-md-4 > p"), driver);

    public boolean aboutUsTextIsDisplayed() {
        return aboutUsText.findWebElements().size() == 1;
    }
}
