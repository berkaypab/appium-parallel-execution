package com.appium.tg.util.selector;

import com.appium.tg.util.modal.ElementInfo;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class AndroidSelector implements Selector {

    @Override
    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getAndroidType().equals("css")) {
            by = AppiumBy.cssSelector(elementInfo.getAndroidValue());
        } else if (elementInfo.getAndroidType().equals("id")) {
            by = AppiumBy.id(elementInfo.getAndroidValue());
        } else if (elementInfo.getAndroidType().equals("xpath")) {
            by = AppiumBy.xpath(elementInfo.getAndroidValue());
        } else if (elementInfo.getAndroidType().equals("class")) {
            by = AppiumBy.className(elementInfo.getAndroidValue());
        }
        else if (elementInfo.getAndroidType().equals("text")){
            by = AppiumBy.linkText(elementInfo.getAndroidValue());
        }else if (elementInfo.getAndroidType().equals("accessibilityId")){
            by = AppiumBy.accessibilityId(elementInfo.getAndroidValue());
        }
        return by;
    }

    @Override
    public int getElementInfoToIndex(ElementInfo elementInfo) {
        return elementInfo.getAndroidIndex();
    }
}
