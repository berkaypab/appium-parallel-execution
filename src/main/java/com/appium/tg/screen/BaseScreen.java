package com.appium.tg.screen;

import com.appium.tg.constants.CommonConstants;
import com.appium.tg.util.modal.SelectorInfo;
import com.appium.tg.util.selector.Selector;
import com.appium.tg.util.selector.SelectorFactory;
import com.appium.tg.util.selector.SelectorType;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.annotation.Nullable;
import java.util.List;

import static com.appium.tg.constants.CommonConstants.MOBILE_PLATFORM_NAME;
import static com.appium.tg.constants.DriverConstants.ANDROID;
import static com.appium.tg.constants.DriverConstants.APPIUM_EXPLICIT_WAIT;


public class BaseScreen {

    public final WebDriver driver;
    public final WebDriverWait wait;
    protected static Selector selector;

    public BaseScreen(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, APPIUM_EXPLICIT_WAIT);
        selector = SelectorFactory.createElementHelper(MOBILE_PLATFORM_NAME.equalsIgnoreCase("Android") ? SelectorType.ANDROID : SelectorType.IOS);
    }

    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement scrollToElement(String elementText) {
        WebElement element;

        if (MOBILE_PLATFORM_NAME.equalsIgnoreCase(ANDROID)) {
            element = driver
                    .findElement(
                            AppiumBy.androidUIAutomator(
                                    "new UiScrollable(new UiSelector().scrollable(true))"
                                            + ".scrollIntoView(new UiSelector().text(\"" + elementText + "\"))"
                            )
                    );
        } else {
            element = driver.findElement(AppiumBy.iOSNsPredicateString("label == '" + elementText + "'"));
        }

        return element;
    }

    public void tap(By by) {
        waitUntilElementVisible(by);
        driver.findElement(by).click();
    }

    public void scrollAndTap(String elementText) {
        scrollToElement(elementText).click();
    }

    public void inputText(By by, String text) {
        waitUntilElementVisible(by);
        driver.findElement(by).sendKeys(text);
    }

    public void scrollAndInputText(String elementText, String text) {
        scrollToElement(elementText).sendKeys(text);
    }
    public List<WebElement> findElements(By by) throws Exception {
        List<WebElement> webElementList = null;
        try {
            webElementList = wait.until(new ExpectedCondition<List<WebElement>>() {
                @Nullable
                @Override
                public List<WebElement> apply(@Nullable WebDriver driver) {
                    List<WebElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });
            if (webElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }
        } catch (Exception e) {
            throw e;
        }
        return webElementList;
    }

    public WebElement findElement(By by) throws Exception {
        WebElement element;
        try {
            element = findElements(by).get(0);
        } catch (Exception e) {
            throw e;
        }
        return element;
    }

    public WebElement findElementByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        WebElement element = null;
        try {
            element = selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                    .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
        } catch (Exception e) {
            //Assertions.fail("key = %s by = %s Element not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> findElementsByKey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        List<WebElement> elements = null;
        try {
            elements = findElements(selectorInfo.getBy());
        } catch (Exception e) {
            //Assertions.fail("key = %s by = %s Elements not found ", key, selectorInfo.getBy().toString());
            e.printStackTrace();
        }
        return elements;
    }
}
