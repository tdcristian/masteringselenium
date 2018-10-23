package com.masteringselenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.masteringselenium.config.DriverFactory;
import com.masteringselenium.listeners.ScreenshotListeners;
import net.lightbody.bmp.BrowserMobProxy;

@Listeners(ScreenshotListeners.class)
public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(
            new ArrayList<DriverFactory>());
    
    private static ThreadLocal<DriverFactory> driverThread;
    
    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverThread = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory webDriverThread = new DriverFactory();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }
    
    public static RemoteWebDriver getDriver() {
        return driverThread.get().getDriver();
    }

    public static BrowserMobProxy getBrowserMobProxy() {
        return driverThread.get().getBrowserMobProxy();
    }
    
    public static RemoteWebDriver getBrowserMobProxyEnabledDriver() {
        return driverThread.get().getDriver(true);
    }
    
    @AfterMethod(alwaysRun = true)
    public static void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }
    
    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory webDriverThread :
                webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }
}
