package com.masteringselenium.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePO {
    
    @FindBy(how = How.NAME, using = "id")
    private WebElement usernameField;

    @FindBy(how = How.NAME, using = "pass")
    private WebElement passwordField;
    
    @FindBy(how = How.CSS,using = "#middlecolumn > center > strong > table > tbody > tr:nth-child(4) > td:nth-child(1) > center > input[type=\"button\"]")
    private WebElement loginButtonLocator;

    public LoginPagePO(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void logInWithUserNameAndPassword(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButtonLocator.click();
    }    

}
