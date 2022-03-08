package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    //Wait methods
    public WebElement waitForElementPresented(String locator, long timeout, String errorMessage) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForElementsPresented(String locator, long timeout, String errorMessage) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
    }

    public void waitForElementAndClick(String locator, long timeout, String errorMessage) {
        WebElement element = waitForElementPresented(locator, timeout, errorMessage);
        element.click();
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresented(locator, timeoutInSeconds, errorMessage);
        element.clear();
        return element;
    }


    //Assert method
    public void assertElementHasText(WebElement element, String expectedText, String errorMessage) {
        try {
            String actualText = element.getText();
            Assert.assertEquals(errorMessage, expectedText, actualText);
        } catch (Exception ex){
            System.out.println("Element not found");
        }
    }

    public boolean assertElementPresent(String locator) {
        By by = this.getLocatorByString(locator);
        boolean result = false;
        try {
            if (driver.findElement(by).isDisplayed()) {
                result = true;
            }
        } catch (Exception ex) {
            System.out.println("Can't find element");
        }
        return result;
    }


    // Help methods
    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension screenSize = driver.manage().window().getSize();
//        int x = screenSize.width / 2;
//        int startY = (int) (screenSize.height * 0.8);
//        int endY = (int) (screenSize.height * 0.2);
//        action
//                .press(x, startY)
//                .waitAction(timeOfSwipe)
//                .moveTo(x, endY)
//                .release()
//                .perform();
    }

    public void quickSwipeUp() {
        swipeUp(200);
    }

    public void swipeUpToElement(String locator, int maxSwipes, String errorMessage) {
        By by = this.getLocatorByString(locator);
        int currentSwipes = 0;
        while (driver.findElements(by).size() == 0) {
            if (currentSwipes > maxSwipes) {
                waitForElementPresented(locator, 0, "Element not found after all swipes");
                return;
            }
            quickSwipeUp();
            ++currentSwipes;
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        WebElement element = waitForElementPresented(locator, 10, errorMessage);
        swipeElementToLeft(element);
    }

    public void swipeElementToLeft(WebElement element) {
        System.out.println(driver.manage().window().getSize());
        int leftX = element.getLocation().getX()+10;
        int rightX = leftX + element.getSize().getWidth()-10;
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        System.out.println(">>>" + rightX + "<<<< >>>>" + middleY + "<<<");
//        action.tap(rightX, middleY).perform();
        //TODO this actions isn't worked
//        action
//                .press(rightX, middleY)
//                .waitAction(300)
//                .moveTo(leftX, middleY)
//                .release()
//                .perform();
    }

    private By getLocatorByString(String locatorWithType) {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator. Locator is: " + locatorWithType);
        }
    }


}
