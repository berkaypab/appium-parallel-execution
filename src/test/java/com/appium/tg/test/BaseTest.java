package com.appium.tg.test;

import com.appium.tg.screen.ScreenProvider;
import com.appium.tg.util.TestListener;
import com.appium.tg.util.driver.MobileDriverFactory;
import com.appium.tg.util.driver.MobileDriverService;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.time.Duration;

import static com.appium.tg.constants.CommonConstants.EXECUTION_ENV_NAME;
import static com.appium.tg.constants.CommonConstants.MOBILE_PLATFORM_NAME;
import static com.appium.tg.util.driver.MobileDriverHolder.getDriver;
import static com.appium.tg.util.driver.MobileDriverHolder.setDriver;

@Listeners(TestListener.class)
public class BaseTest {
    private final MobileDriverService driverService = new MobileDriverFactory().getDriverService();
    private AppiumDriverLocalService appiumService;
    private final Logger logger = LogManager.getLogger();

    @BeforeSuite
    public void oneTimeSetup() {
        logger.debug("Test execution platform: {}", MOBILE_PLATFORM_NAME);
        logger.debug("Test execution environment: {}", EXECUTION_ENV_NAME);
    }

    @BeforeMethod
    public void openApp() {
        appiumService = driverService.startAppiumService();
        driverService.spinUpDriver(appiumService);
        setDriver(driverService.getDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public ScreenProvider screen() {
        return new ScreenProvider(getDriver());
    }

//    public UiComponentProvider uiComponent() {
//        return new UiComponentProvider(getDriver());
//    }

    @AfterMethod
    public void closeApp() {
        driverService.closeDriver();
        driverService.stopAppiumService(appiumService);
    }
}
