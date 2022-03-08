package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
        LEARN_MORE_LINK = "Learn more about Wikipedia",
        NEW_WAY_TO_EXPLORE = "New way to explore",
        ADD_OR_EDIT_PREFERRED_LANG = "Add or edit preferred languages",
        LEARN_MORE_ABOUT_DATA_COLLECTED = "Learn more about data collected",
        NEXT_LINK = "Next",
        GET_STARTED_BUTTON = "Get started";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresented(LEARN_MORE_LINK,
                10, "'Learn more' is not visible");
    }

    public void waitForNewWayToExplore() {
        this.waitForElementPresented(NEW_WAY_TO_EXPLORE,
                10, "'New way to explore' is not visible");
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresented(ADD_OR_EDIT_PREFERRED_LANG,
                10, "'Add or edit preferred languages' is not visible");
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresented(LEARN_MORE_ABOUT_DATA_COLLECTED,
                10, "'Learn more about data collected' is not visible");
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK,
                10, "Can't find and click on 'Next' link");
    }

    public void clickOnGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                10, "Can't find and click on 'Get started' button");
    }

}
