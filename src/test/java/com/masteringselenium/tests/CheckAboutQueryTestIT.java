package com.masteringselenium.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;

public class CheckAboutQueryTestIT {

    @Test
    @Ignore
    public void logInInTheWebsiteStep4() {

        DriverBase.getDriver().get("http://www.javascriptkit.com/script/cut76.shtml");
        
        com.masteringselenium.query_page_objects.LoginPage loginPage = new com.masteringselenium.query_page_objects.LoginPage();
        loginPage.logInWithUsernameAndPassword("JavaScript","Kit");

        Assertions.assertThat(DriverBase.getDriver().getTitle()).isEqualTo("example 2");
    }
}
