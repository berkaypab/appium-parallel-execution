package com.appium.tg.test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("test12 ho hooo");
//        screen().getNavigationBarScreen()
//                .tapOnLoginIcon();
//        screen().getLoginScreen()
//                .login(EMAIL_ADDRESS, PASSWORD);
//        assertEquals(
//                uiComponent().getAlertScreen().getAlertTitle(),
//                LOGIN_ALERT.getAlertTitle()
//        );
//        assertEquals(
//                uiComponent().getAlertScreen().getAlertMessage(),
//                LOGIN_ALERT.getAlertMessage()
//        );
    }
}
