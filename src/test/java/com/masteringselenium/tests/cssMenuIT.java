package com.masteringselenium.tests;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;

public class cssMenuIT extends DriverBase {

    private RemoteWebDriver driver;
 

    @BeforeTest
    public void bindDriver() {
        driver = getDriver();
    }

    @Test
    @Ignore
    public void automateCSSMenu() {
        driver.get("http://web.masteringselenium.com/cssMenu.html");

        Actions advancedActions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5, 100);

        WebElement servicesMenuOption = driver.findElement(By.id("services"));
        WebElement webDevelopmentSubMenuOption = driver.findElement(By.cssSelector("#services > ul > li:nth-child(2)"));

        advancedActions.moveToElement(servicesMenuOption).perform();
        wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenuOption));

        advancedActions.moveToElement(webDevelopmentSubMenuOption).click().perform();
    }

    @Test
    @Ignore
    public void automateJavaScriptDragAndDrop() {
        driver.get("http://web.masteringselenium.com/jsDragAndDrop.html");

        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));
        WebElement firstBox = driver.findElement(By.id("one"));
        WebElement secondBox = driver.findElement(By.id("two"));

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.clickAndHold(firstBox)
                .moveToElement(obliterator)
                .release()
                .perform();

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);

        advancedActions.dragAndDrop(secondBox, obliterator).perform();

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(3);
    }

    @Test
    @Ignore
    public void automateJavaScriptDragAndDropWithOffsetsStep1() {
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");

        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));

        WebElement firstBox = driver.findElement(By.id("one"));

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.moveToElement(firstBox).moveByOffset(-40, 0).clickAndHold()
                .moveToElement(obliterator)
                .release()
                .perform();
        
        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
    }
    
    @Test
    @Ignore
    public void automateJavaScriptDragAndDropWithOffsetsStep2() {
        
        driver = getDriver();
        driver.get("http://web.masteringselenium.com/jsDragAndDropWithHandle.html");

        Actions advancedActions = new Actions(driver);
        final By destroyableBoxes = By.cssSelector("ul > li > div");
        WebElement obliterator = driver.findElement(By.id("obliterate"));

        WebElement firstBox = driver.findElement(By.id("one"));

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);

        advancedActions.moveToElement(firstBox,-40,0)
                .clickAndHold()
                .moveToElement(obliterator)
                .release()
                .perform();

        Assertions.assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
    }

}
