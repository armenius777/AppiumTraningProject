package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Locators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


    //Wait methods
    public WebElement waitForElementPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitForElementsPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
    }

    public WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresented(by,timeoutInSeconds, errorMessage);
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

    public boolean assertElementPresent(By by) {
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
        int x = screenSize.width / 2;
        int startY = (int) (screenSize.height * 0.8);
        int endY = (int) (screenSize.height * 0.2);
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    public void quickSwipeUp() {
        swipeUp(200);
    }

    public void swipeUpToElement(By by, int maxSwipes, String errorMessage) {
        int currentSwipes = 0;
        while (driver.findElements(by).size() == 0) {
            if (currentSwipes > maxSwipes) {
                waitForElementPresented(by, 0, "Element not found after all swipes");
                return;
            }
            quickSwipeUp();
            ++currentSwipes;
        }
    }

    public void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresented(by, 10, errorMessage);
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
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }


}
