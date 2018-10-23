package com.masteringselenium.config;

import static org.openqa.selenium.Proxy.ProxyType.*;
import static org.openqa.selenium.remote.CapabilityType.*;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;

public class DriverFactory {

    private RemoteWebDriver webDriver;

    private DriverType selectedDriverType;

    private BrowserMobProxy browserMobProxy;
    private boolean usingBrowserMobProxy = false;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();

    private final String systemArchitecture = System.getProperty("os.arch");

    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    private final boolean headless = Boolean.getBoolean("headless");

    private final String threads = System.getProperty("threadCount");

    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    
    private final String proxyHostname = System.getProperty("proxyHost");
    
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    
    private final String proxyDetails = String.format("%s:%d",proxyHostname,proxyPort);
    
    public DriverFactory() {
        DriverType driverType = DriverType.FIREFOX;
        String browser = System.getProperty("browser", driverType.toString().toUpperCase());

        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ex) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public BrowserMobProxy getBrowserMobProxy() {
        if (usingBrowserMobProxy) {
            return browserMobProxy;
        }
        return null;
    }

    public RemoteWebDriver getDriver(boolean useBrowserMobProxy)  {
        if (useBrowserMobProxy != usingBrowserMobProxy) {
            quitDriver();
        }
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType, useBrowserMobProxy);
        }

        return webDriver;
    }

    public RemoteWebDriver getDriver()  {
            return getDriver(usingBrowserMobProxy);
    }
    
    private void instantiateWebDriver(DriverType driverType,boolean useBrowserMobProxy) {
        
        
        System.out.println(" ");
        System.out.println("Local operating system: " + operatingSystem);
        System.out.println("Local architecture: " + systemArchitecture);
        System.out.println("Selected browser: " + selectedDriverType);
        System.out.println("Running headless: " + headless);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (proxyEnabled || useBrowserMobProxy) {
            Proxy proxy;
            if (useBrowserMobProxy) {
                usingBrowserMobProxy = true;
                browserMobProxy = new BrowserMobProxyServer();
                browserMobProxy.start();
                if (proxyEnabled) {
                    browserMobProxy.setChainedProxy(new InetSocketAddress(proxyHostname, proxyPort));
                }
                proxy = ClientUtil.createSeleniumProxy(browserMobProxy);
            } else {
                proxy = new Proxy();
                proxy.setProxyType(MANUAL);
                proxy.setHttpProxy(proxyDetails);
                proxy.setSslProxy(proxyDetails);
            }
            desiredCapabilities.setCapability(PROXY, proxy);
        }
        
        if (useRemoteWebDriver) {
            URL seleniumGridURL = null;
            try {
                seleniumGridURL = new URL(System.getProperty("gridURL"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (desiredPlatform != null && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }
            if (desiredBrowserVersion != null && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }
            desiredCapabilities.setBrowserName(selectedDriverType.toString().toLowerCase());
            desiredCapabilities.setCapability("platformName", "windows");
            webDriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            webDriver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
            usingBrowserMobProxy = false;
        }
    }
}
