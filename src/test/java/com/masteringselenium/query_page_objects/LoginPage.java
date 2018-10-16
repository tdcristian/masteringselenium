package com.masteringselenium.query_page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lazerycode.selenium.util.Query;

public class LoginPage extends BasePage {

    private Query usernameField = new Query(By.name("id"), driver);

    private Query passwordField = new Query(By.name("pass"), driver);

    private Query loginButton = new Query(By.cssSelector(
            "#middlecolumn > center > strong > table > tbody > tr:nth-child(4) > td:nth-child(1) > center > " +
                    "input[type=\"button\"]"),
            driver);

    public void logInWithUsernameAndPassword(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver,15,200);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField.locator()));
        
        usernameField.findWebElement().sendKeys(username);
        passwordField.findWebElement().sendKeys(password);
        loginButton.findWebElement().click();
    }
}
