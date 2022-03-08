package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends MainPageObject {

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnContinue() {
        WebElement continueButton = waitForElementPresented(Locators.CONTINUE_BUTTON_ID,
                3, "Continue button is missing");
        continueButton.click();
    }

    public WebElement waitForNewWaysShown() {
        return waitForElementPresented(Locators.NEW_WAYS_ID, 5, "New ways text is missing");
    }

    public void clickOnHomepageSearchField() {
        WebElement homepageSearchField =
                waitForElementPresented(Locators.HOMEPAGE_SEARCH_FIELD_XPATH,
                        5, "Search field is missing in the HomePage");
        homepageSearchField.click();
    }

    public void skipIntro() {
        WebElement skipButton =
                waitForElementPresented(Locators.SKIP_BUTTON_ID, 10, "SKIP button is missing");
        skipButton.click();
    }

    public void goToSavedPublicationsFolder() {
        WebElement savedPublicationsPageButton = waitForElementPresented(Locators.SAVED_BUTTON_XPATH, 10,
                "Saved button is missing");
        savedPublicationsPageButton.click();
        WebElement savedPublicationsFolder = waitForElementPresented(Locators.SAVED_PUBLICATIONS_FOLDER_ID, 10,
                "Saved publications folder is missing");
        savedPublicationsFolder.click();
    }


}
