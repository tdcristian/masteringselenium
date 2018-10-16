package com.masteringselenium.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;
import com.masteringselenium.fluent_page_objects.LoginPage;

public class CheckAboutPageFluentIT {

    @Test
    @Ignore
    public void logInToTheWebSite() {
        DriverBase.getDriver().get("http://www.javascriptkit.com/script/cut76.shtml");

        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("JavaScript").enterPassword("Kit").andLogin();
        
        Assertions.assertThat(DriverBase.getDriver().getTitle()).isEqualTo("example 2");
    }
}
