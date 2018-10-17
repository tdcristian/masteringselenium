package com.masteringselenium.tests;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;
import com.masteringselenium.accessKeys.Browser;

public class AccessKeyIT extends DriverBase {

    private RemoteWebDriver driver;

    @BeforeTest
    public void bindDriver() {
        driver = getDriver();
    }

    @Test
    public void testThatUsingAccessKeysWorks() {
        driver = getDriver();
        driver.get("http://web.masteringselenium.com/accessKeysHome.html");

        WebDriverWait wait = new WebDriverWait(driver, 5, 100);
        List<WebElement> home = driver.findElements(By.id("home"));

        Assertions.assertThat(home.size()).isEqualTo(1);

        triggerAccessKey("9");
        
        WebElement access = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about")));
        
        home = driver.findElements(By.id("home"));
        Assertions.assertThat(home.size()).isEqualTo(0);
        
        Assertions.assertThat(access.isDisplayed()).isTrue();
    }

    private void triggerAccessKey(String accessKey) {
        Actions advancedActions = new Actions(driver);
        Platform currentOS = driver.getCapabilities().getPlatform();
        Platform currentOSFamily = (currentOS.family() == null ? currentOS : currentOS.family());

        String currentBrowserName = driver.getCapabilities().getBrowserName();
        Browser currentBrowser = Browser.getBrowserType(currentBrowserName);

        switch (currentOSFamily) {
            case WINDOWS:
                /*
                is working just on CHROME
                 */
                advancedActions.keyDown(Keys.ALT)
                        .keyDown(Keys.SHIFT)
                        .sendKeys(accessKey)
                        .keyUp(Keys.SHIFT)
                        .keyUp(Keys.ALT)
                        .perform();
                break;
            default:
                throw new IllegalArgumentException("Unrecognised operating system name '" + currentOS + "'");
        }
    }

}
