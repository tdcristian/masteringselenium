package com.masteringselenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public static By usernameLocator = By.name("id");

    public static By passwordLocator = By.name("pass");

    public static By loginButtonLocator = By.cssSelector("#middlecolumn > center > strong > table > tbody > tr:nth-child(4) > td:nth-child(1) > center > input[type=\"button\"]");

    public static void logInWithUsernameAndPassword(String username, String password, WebDriver driver) {
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }
    
}
