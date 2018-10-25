//package com.masteringselenium.tests;
//
//import static org.assertj.core.api.Assertions.*;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import com.masteringselenium.DriverBase;
//
//public class InjectJavaScriptLibraryIT extends DriverBase {
//
//    private RemoteWebDriver driver;
//
//    @Test
//    @Ignore
//    public void injectjQueryIntoGoogle() {
//        driver = getDriver();
//        driver.get("http://www.google.com");
//        WebDriverWait wait = new WebDriverWait(driver,15,100);
//
//        assertThat(isjQueryLoaded()).isEqualTo(false);
//
//        injectScript("https://code.jquery.com/jquery-latest.min.js");
//        
//        wait.until(jQueryHasLoaded());
//        
//        assertThat(isjQueryLoaded()).isEqualTo(true);
//    }
//
//    private static ExpectedCondition<Boolean> jQueryHasLoaded() {
//        return driver1 -> {
//            JavascriptExecutor js = (JavascriptExecutor) driver1;
//            return Boolean.valueOf(js.executeScript("return typeof jQuery != 'undefined';").toString());
//        };
//    }
//
//    private void injectScript(String scriptURL) {
//        driver.executeScript("function injectScript(url) {\n" +
//                "    var script = document.createElement('script');\n" +
//                "    script.src = url;\n" +
//                "    var head = document.getElementsByTagName('head')[0];\n" +
//                "    head.appendChild(script);\n" +
//                "}\n" +
//                "\n" +
//                "var scriptURL = arguments[0];\n" +
//                "injectScript(scriptURL);", scriptURL);
//    }
//
//    private Boolean isjQueryLoaded() {
//        return (Boolean) driver.executeScript("return typeof jQuery != 'undefined';");
//    }
//    
//    @Test
//    @Ignore
//    public void javascriptExample() {
//        driver = getDriver();
//        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
//        driver.executeAsyncScript("var callback = arguments[arguments.length - 1]; " +
//                "window.setTimeout(callback, 25000);");
//        
//        driver.get("http://www.google.com");
//    }
//
//}
