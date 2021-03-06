package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import lib.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PublicationsPage extends MainPageObject {

    public PublicationsPage(AppiumDriver driver) {
        super(driver);
    }

    public List<WebElement> getSavedPublications() {
        return waitForElementsPresented(Locators.SAVED_PUBLICATIONS_LIST_XPATH, 10,
                "Saved publications are missing");
    }

    public void deleteFirstSavedPublication() {
        WebElement firstPublication = waitForElementPresented(Locators.FIRST_SAVED_PUBLICATION_XPATH,
                10, "Some Error");

        TouchAction action = new TouchAction(driver);
        action.longPress((LongPressOptions) firstPublication);
        action.perform();
        quickSwipeUp();
        WebElement removeButton = waitForElementPresented(Locators.REMOVE_BUTTON_ID,
                10, "Remove button is not shown");
        removeButton.click();
    }

}
