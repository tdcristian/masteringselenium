//package com.masteringselenium.tests;
//
//import java.util.concurrent.TimeUnit;
//
//import org.assertj.core.api.Assertions;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import com.masteringselenium.DriverBase;
//import com.masteringselenium.page_objects.AboutPage;
//import com.masteringselenium.page_objects.IndexPage;
//import com.masteringselenium.page_objects.LoginPage;
//import com.masteringselenium.page_objects.LoginPagePO;
//
//public class CheckAboutPageIT extends DriverBase {
//
//    private WebDriver driver;
//
//    @BeforeMethod
//    public void setup() {
//        driver = getDriver();
//    }
//
//    @Test
//    @Ignore
//    public void goToTheAboutPage() {
//        driver.get("http://web.masteringselenium.com/index.html");
//
//        WebElement aboutLink = driver.findElement(IndexPage.aboutLinkLocator);
//        aboutLink.click();
//
//        WebElement aboutHeading = driver.findElement(AboutPage.aboutHeadingLocator);
//        Assertions.assertThat(aboutHeading.getText()).isEqualTo("About us!");
//    }
//
//    @Test
//    @Ignore
//    public void loginToTheWebsite() {
//        driver.get("http://www.javascriptkit.com/script/cut76.shtml");
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//
//        WebElement username = driver.findElement(LoginPage.usernameLocator);
//        WebElement password = driver.findElement(LoginPage.passwordLocator);
//        WebElement submitButton = driver.findElement(LoginPage.loginButtonLocator);
//
//        username.sendKeys("JavaScript");
//        password.sendKeys("Kit");
//        submitButton.click();
//
//        Assertions.assertThat(driver.getTitle()).isEqualTo("example 2");
//    }
//
//    @Test
//    @Ignore
//    public void logInToTheWebsiteStep2() {
//        driver.get("http://www.javascriptkit.com/script/cut76.shtml");
//        LoginPage.logInWithUsernameAndPassword("JavaScript", "Kit", driver);
//
//        Assertions.assertThat(driver.getTitle()).isEqualTo("example 2");
//    }
//    
//    @Test
//    @Ignore
//    public void logInTheWebsiteStep3() {
//        driver.get("http://www.javascriptkit.com/script/cut76.shtml");
//
//        LoginPagePO loginPagePO = new LoginPagePO(driver);
//        loginPagePO.logInWithUserNameAndPassword("JavaScript","Kit");
//
//        Assertions.assertThat(driver.getTitle()).isEqualTo("example 2");
//    }
//    
//
//}
