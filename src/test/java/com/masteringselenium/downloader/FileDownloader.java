package com.masteringselenium.downloader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FileDownloader {

    private RequestType httpRequestMethod = RequestType.GET;

    private URI fileURI;

    private List<NameValuePair> urlParameters;

    private RemoteWebDriver driver;

    public FileDownloader(RemoteWebDriver driverObject) {
        this.driver = driverObject;
    }

    private String getWebDriverUserAgent() {
        return driver.executeScript("return navigator.userAgent").toString();
    }
    
    public BasicCookieStore getWebDriverCookies(Set<Cookie> seleniumCookieSet) {
        BasicCookieStore copyOfWebDriverCookieStore = new BasicCookieStore();
        for (Cookie seleniumCookie :
                seleniumCookieSet) {
            BasicClientCookie duplicateCookie = new BasicClientCookie(seleniumCookie.getName(),
                    seleniumCookie.getValue());
            duplicateCookie.setDomain(seleniumCookie.getDomain());
            duplicateCookie.setSecure(seleniumCookie.isSecure());
            duplicateCookie.setExpiryDate(seleniumCookie.getExpiry());
            duplicateCookie.setPath(seleniumCookie.getPath());
            copyOfWebDriverCookieStore.addCookie(duplicateCookie);
        }
        return copyOfWebDriverCookieStore;
    }

    public void setHttpRequestMethod(RequestType httpRequestMethod) {
        this.httpRequestMethod = httpRequestMethod;
    }

    public void setURI(URI fileURI) {
        this.fileURI = fileURI;
    }

    public void setUrlParameters(List<NameValuePair> urlParameters) {
        this.urlParameters = urlParameters;
    }

    private HttpResponse makeHttpConnection() throws IOException {
        if (fileURI == null) {
            throw new NullPointerException("No URI file specified");
        }
        HttpClient client = HttpClientBuilder.create().build();

        HttpRequestBase requestMethod = httpRequestMethod.getRequestMethod();
        requestMethod.setURI(fileURI);
        
        BasicHttpContext localContent = new BasicHttpContext();

        localContent.setAttribute(HttpClientContext.COOKIE_STORE,getWebDriverCookies(driver.manage().getCookies()));
        requestMethod.setHeader("User-Agent",getWebDriverUserAgent());
        
        if (urlParameters != null && (httpRequestMethod.equals(RequestType.PATCH) ||
                httpRequestMethod.equals(RequestType.POST) ||
                httpRequestMethod.equals(RequestType.PUT))) {
            try {
                ((HttpEntityEnclosingRequestBase) requestMethod).setEntity(new UrlEncodedFormEntity(urlParameters));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return client.execute(requestMethod, localContent);
        
    }
    
    public int getLinkHTTPStatus() {
        HttpResponse downloadableFile = null;

        try {
            downloadableFile = makeHttpConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int httpStatusCode;
        try {
            httpStatusCode = downloadableFile.getStatusLine().getStatusCode();
        } finally {
            if (downloadableFile.getEntity() != null) {
                try {
                    downloadableFile.getEntity().getContent().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStatusCode;
    }
        
    public File downloadFile() throws IOException {
        File downloadedFile = null;
        try {
            downloadedFile = File.createTempFile("download",".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpResponse fileToDownload = null;
        try {
            fileToDownload = makeHttpConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileUtils.copyInputStreamToFile(fileToDownload.getEntity().getContent(),downloadedFile);
        } finally {
            fileToDownload.getEntity().getContent().close();
        }
        return downloadedFile;
    }
    
}
