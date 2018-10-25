//package com.masteringselenium.tests;
//
//import static org.assertj.core.api.Assertions.*;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import com.masteringselenium.DriverBase;
//
//public class DragAndDropIT extends DriverBase {
//
//    private RemoteWebDriver driver;
//    
//    @Test
//    @Ignore
//    public void dragAndDropHTML5() {
//        driver = getDriver();
//        driver.get("http://web.masteringselenium.com/dragAndDrop.html");
//
//
//        By destroyableBoxes = By.cssSelector("ul > li > a");
//        WebElement obliterator = driver.findElement(By.id("obliterate"));
//        WebElement firstBox = driver.findElement(By.id("one"));
//        WebElement secondBox = driver.findElement(By.id("two"));
//
//        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(5);
//
//        simulateDragAndDrop(firstBox, obliterator);
//
//        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(4);
//
//        simulateDragAndDrop(secondBox, obliterator);
//
//        assertThat(driver.findElements(destroyableBoxes).size()).isEqualTo(3);
//    }
//
//    private void simulateDragAndDrop(WebElement elementToDrag, WebElement target) {
//        driver.executeScript("function createEvent(typeOfEvent) {\n" +
//                        "    var event = document.createEvent(\"CustomEvent\");\n" +
//                        "    event.initCustomEvent(typeOfEvent, true, true, null);\n" +
//                        "    event.dataTransfer = {\n" +
//                        "        data: {},\n" +
//                        "        setData: function (key, value) {\n" +
//                        "            this.data[key] = value;\n" +
//                        "        },\n" +
//                        "        getData: function (key) {\n" +
//                        "            return this.data[key];\n" +
//                        "        }\n" +
//                        "    };\n" +
//                        "    return event;\n" +
//                        "}\n" +
//                        "\n" +
//                        "function dispatchEvent(element, event, transferData) {\n" +
//                        "    if (transferData !== undefined) {\n" +
//                        "        event.dataTransfer = transferData;\n" +
//                        "    }\n" +
//                        "    if (element.dispatchEvent) {\n" +
//                        "        element.dispatchEvent(event);\n" +
//                        "    } else if (element.fireEvent) {\n" +
//                        "        element.fireEvent(\"on\" + event.type, event);\n" +
//                        "    }\n" +
//                        "}\n" +
//                        "\n" +
//                        "function simulateHTML5DragAndDrop(element, target) {\n" +
//                        "    var dragStartEvent = createEvent('dragstart');\n" +
//                        "    dispatchEvent(element, dragStartEvent);\n" +
//                        "    var dropEvent = createEvent('drop');\n" +
//                        "    dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n" +
//                        "    var dragEndEvent = createEvent('dragend');\n" +
//                        "    dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n" +
//                        "}\n" +
//                        "\n" +
//                        "var elementToDrag = arguments[0];\n" +
//                        "var target = arguments[1];\n" +
//                        "simulateHTML5DragAndDrop(elementToDrag, target);",
//                elementToDrag, target);
//    }
//}
