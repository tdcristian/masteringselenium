package com.masteringselenium.fluent_page_objects;

import org.openqa.selenium.By;

import com.lazerycode.selenium.util.Query;
import com.masteringselenium.page_objects_query.BasePage;

public class LoginPage extends BasePage {

    private Query usernameField = new Query(By.name("id"), driver);

    private Query passwordField = new Query(By.name("pass"), driver);

    private Query loginButton = new Query(By.cssSelector(
            "#middlecolumn > center > strong > table > tbody > tr:nth-child(4) > td:nth-child(1) > center > " +
                    "input[type=\"button\"]"),
            driver);
    
    public LoginPage enterUsername(String username) {
        usernameField.findWebElement().sendKeys(username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        passwordField.findWebElement().sendKeys(password);
        return this;
    }
    
    public void andLogin() {
        loginButton.findWebElement().click();
    }
    
}
