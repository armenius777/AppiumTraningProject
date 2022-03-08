package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();

        driver = getDriverByPlatformEnv(capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.closeApp();
        driver.quit();

        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "TestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\AppiumTraningProject\\apks\\org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\AppiumTraningProject\\apks\\Wikipedia.app");
        } else {
            throw new Exception("Can't get run platform from env variable. Platform value is: " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");
        if (platform.equals(PLATFORM_ANDROID)) {
            return new AndroidDriver(new URL(appiumURL), capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            return new IOSDriver(new URL(appiumURL), capabilities);
        } else {
            throw new Exception("Can't get run platform from env variable. Platform value is: " + platform);
        }
    }


}
