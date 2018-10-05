package com.masteringselenium.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private RemoteWebDriver webDriver;

    private DriverType selectedDriverType;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();

    private final String systemArchitecture = System.getProperty("os.arch");

    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    private final boolean headless = Boolean.getBoolean("headless");

    private final String threads = System.getProperty("threadCount");

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

    public RemoteWebDriver getDriver() {
        if (webDriver == null) {
            try {
                instantiateNewDriver(selectedDriverType);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return webDriver;
    }

    private void instantiateNewDriver(DriverType driverType) throws MalformedURLException {
        
        
        System.out.println(" ");
        System.out.println("Local operating system: " + operatingSystem);
        System.out.println("Local architecture: " + systemArchitecture);
        System.out.println("Selected browser: " + selectedDriverType);
        System.out.println("Running headless: " + headless);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
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
        }
    }
}
