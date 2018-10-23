package com.masteringselenium.tests;

import static org.assertj.core.api.Assertions.*;

import java.net.MalformedURLException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

public class BrowserMobProxyIT extends DriverBase {


    private RemoteWebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getBrowserMobProxyEnabledDriver();
    }

    @Test
    public void usingAProxyToTrackNetworkTrafficStep2() {
        getBrowserMobProxy().newHar();
        driver.get("https://www.google.co.uk");

        Har httpArchive = getBrowserMobProxy().getHar();

        assertThat(getHTTPStatusCode("https://www.google.co.uk/", httpArchive))
                .isEqualTo(200);
    }

    private int getHTTPStatusCode(String expectedURL, Har httpArchive) {
        for (HarEntry entry : httpArchive.getLog().getEntries()) {
            if (entry.getRequest().getUrl().equals(expectedURL)) {
                return entry.getResponse().getStatus();
            }
        }

        return 0;
    }
}
