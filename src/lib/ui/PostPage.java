package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PostPage extends MainPageObject {

    public PostPage(AppiumDriver driver) {
        super(driver);
    }

    public void savePost() {
        waitForElementPresented(Locators.HEADER_IMAGE_ID, 10, "Header image is not shown");
        clickToSaveButton();
    }

    public void clickToSaveButton() {
        WebElement saveButton = waitForElementPresented(Locators.SAVE_BUTTON_ID,
                10, "Save button is missing");
        saveButton.click();
    }

    public void backToSearchResults() {
        WebElement backToSearch = waitForElementPresented(Locators.BACK_TO_SEARCH_BUTTON_XPATH, 10,
                "Back to search arrow-button is missing");
        backToSearch.click();
    }

}
