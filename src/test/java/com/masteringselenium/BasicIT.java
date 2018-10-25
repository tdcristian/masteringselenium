package com.masteringselenium;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Ignore;
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

    @Test
    @Ignore
    public void googlePrintScreenExample() {
        WebDriver driver = getDriver();
        driver.get("http://www.google.com");
        WebElement field = driver.findElement(By.name("q"));
        field.sendKeys("test screenshot!!!");

        WebElement searchField = driver.findElement(By.name("qqq"));
    }

    private void googleExampleThatSearchesFor(String searchString) {
        WebDriver driver = getDriver();
        driver.get("http://www.google.com");

        Function<WebDriver, WebElement> weFindElementFoo = new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.name("q"));
            }
        };

        Function<WebDriver, Boolean> weFindElementFooo = new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElements(By.name("q")).size() > 0;
            }
        };

        Function<WebDriver, Boolean> didWeFindElementFoooo = webDriver -> webDriver.findElements(
                By.name("q")).size() > 0;

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();
        searchField.sendKeys(searchString);

        System.out.println(" ");
        System.out.println("Page title before: " + driver.getTitle());
        System.out.println(" ");

        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(searchTitleStartWith(searchString));

        System.out.println(" ");
        System.out.println("Page title after: " + driver.getTitle());
    }

    private ExpectedCondition<Boolean> searchTitleStartWith(String searchString) {
        return webDriver -> webDriver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

}
