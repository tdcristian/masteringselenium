package com.masteringselenium.tests;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.masteringselenium.DriverBase;
import com.masteringselenium.downloader.FileDownloader;
import com.masteringselenium.downloader.RequestType;
import com.masteringselenium.utils.CheckFileHash;
import com.masteringselenium.utils.HashType;

public class FileDownloaderIT extends DriverBase {

    private RemoteWebDriver driver;

    @Test
    @Ignore
    public void statusCodeFromEmbeddedFile() throws URISyntaxException {
        driver = getDriver();
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHttpRequestMethod(RequestType.GET);

        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
    }

    @Test
    @Ignore
    public void statusCodeFromEmbeddedImage() throws URISyntaxException {
        driver = getDriver();
        FileDownloader downloadHandler = new FileDownloader(driver);
        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("anImage"));

        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("src"));

        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHttpRequestMethod(RequestType.GET);

        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(404);
    }

    @Test
    @Ignore
    public void downloadAFile() throws URISyntaxException, IOException {
        driver = getDriver();
        FileDownloader downloadHandler = new FileDownloader(driver);

        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));
        downloadHandler.setURI(fileAsURI);
        downloadHandler.setHttpRequestMethod(RequestType.GET);

        File downloadedFile = downloadHandler.downloadFile();

        assertThat(downloadedFile.exists()).isEqualTo(true);
        assertThat(downloadHandler.getLinkHTTPStatus()).isEqualTo(200);
    }

    @Test
    @Ignore
    public void downloadAFileWhilstMimickingSeleniumCookiesAndCheckTheSHA1Hash() throws URISyntaxException,
            IOException {
        driver = getDriver();
        FileDownloader downloaderHandler = new FileDownloader(driver);

        driver.get("http://web.masteringselenium.com/downloadTest.html");
        WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
        URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));
        downloaderHandler.setURI(fileAsURI);
        downloaderHandler.setHttpRequestMethod(RequestType.GET);

        File downloadedFile = downloaderHandler.downloadFile();

        assertThat(downloadedFile.exists()).isEqualTo(true);
        assertThat(downloaderHandler.getLinkHTTPStatus()).isEqualTo(200);
        assertThat(CheckFileHash.generateHashForFileOfType(downloadedFile, HashType.SHA1)).isEqualTo(
                "8882e3d972be82e14a98c522745746a03b97997a");

    }

}
