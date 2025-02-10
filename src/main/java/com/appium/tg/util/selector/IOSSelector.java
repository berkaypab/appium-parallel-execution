package com.appium.tg.util.selector;

import com.appium.tg.util.modal.ElementInfo;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class IOSSelector implements Selector {


    @Override
    public By getElementInfoToBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getIosType().equals("css")) {
            by = AppiumBy.cssSelector(elementInfo.getIosValue());
        } else if (elementInfo.getIosType().equals("id")) {
            by = AppiumBy.id(elementInfo.getIosValue());
        } else if (elementInfo.getIosType().equals("xpath")) {
            by = AppiumBy.xpath(elementInfo.getIosValue());
        } else if (elementInfo.getIosType().equals("class")) {
            by = AppiumBy.className(elementInfo.getIosValue());
        } else if (elementInfo.getIosType().equals("name")) {
            by = AppiumBy.name(elementInfo.getIosValue());
        }else if (elementInfo.getIosType().equals("classChain")){
            by = AppiumBy.iOSClassChain(elementInfo.getIosValue());
        }else if (elementInfo.getIosType().equals("accessibilityId")){
            by = AppiumBy.accessibilityId(elementInfo.getIosValue());
        }
        return by;
    }

    @Override
    public int getElementInfoToIndex(ElementInfo elementInfo) {
        return elementInfo.getIosIndex();
    }
}
