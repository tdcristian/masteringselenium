package com.masteringselenium.config;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AdditionalConditions {

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return (Boolean) ((JavascriptExecutor) webDriver).executeScript(
                        "return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor)
                        driver).executeScript(
                        "return (window.angular !== undefined) && (angular.element(document).injector() !== " +
                                "undefined) && (angular.element(document).injector().get('$http').pendingRequests" +
                                ".length === 0)").toString());
            }
        };
    }
}
