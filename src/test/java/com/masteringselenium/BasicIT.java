package com.masteringselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicIT extends DriverBase {

    @Test
    public void googleCheeseExample() {
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() {
        googleExampleThatSearchesFor("Milk!");
    }

    @Test
    public void googleBananaExample() {
        googleExampleThatSearchesFor("Banana!");
    }

    @Test
    public void googleApplesExample() {
        googleExampleThatSearchesFor("Apples!");
    }

    private void googleExampleThatSearchesFor(String searchString) {
        WebDriver driver = getDriver();
        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));
        
        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println(" ");
        System.out.println("Page title before: "+driver.getTitle());
        System.out.println(" ");
        
        
        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver,10,100);
        wait.until(searchTitleStartWith(searchString));

        System.out.println(" ");
        System.out.println("Page title after: "+driver.getTitle() );
    }

    private ExpectedCondition<Boolean> searchTitleStartWith(String searchString) {
        return webDriver -> webDriver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

}
