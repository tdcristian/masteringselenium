package com.masteringselenium.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private RemoteWebDriver webDriver;

    private DriverType selectedDriverType;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();

    private final String systemArchitecture = System.getProperty("os.arch");

    public DriverFactory() {
        DriverType driverType = DriverType.FIREFOX;
        String browser = System.getProperty("browser", driverType.toString().toUpperCase());
        
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            ignored.fillInStackTrace();
        } catch (NullPointerException ex) {
            ex.fillInStackTrace();
        }
        selectedDriverType = driverType;
    }
    
    public RemoteWebDriver getDriver() {
        if (webDriver == null) {
            instantiateNewDriver(selectedDriverType);
        } 
        return webDriver;
    }

    private void instantiateNewDriver(DriverType driverType) {
        System.out.println(" ");
        System.out.println("Local operating system: "+operatingSystem);
        System.out.println("Local architecture: "+systemArchitecture);
        System.out.println("Selected browser: "+selectedDriverType);
        System.out.println(" ");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        webDriver = driverType.getWebDriverObject(desiredCapabilities);
    }

    public void quitDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
