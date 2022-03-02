package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends MainPageObject {

    public SearchPage(AppiumDriver driver) {
        super(driver);
    }

    public void searchWord(String word) {
        WebElement homepageSearchField =
                waitForElementPresented(By.xpath(Locators.HOMEPAGE_SEARCH_FIELD_XPATH),
                        5, "Search field is missing in the HomePage");
        homepageSearchField.click();
        WebElement searchField =
                waitForElementPresented(By.id(Locators.SEARCH_FIELD_ID), 5, "Search field is missing");
        searchField.sendKeys(word);
        waitForElementPresented(By.id(Locators.SEARCH_RESULTS_AREA_ID), 5, "Search result is empty");
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.id(Locators.RESULTS_TITLE_ID));
    }

    public void backToHome() {
        WebElement backToHome = waitForElementPresented(By.xpath(Locators.BACK_TO_HOME_BUTTON_XPATH), 10,
                "Back to home button is missing");
        backToHome.click();
    }

}
