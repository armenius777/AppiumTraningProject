import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class TestClass {

    private AppiumDriver driver;
    // Locators
    public static final String CONTINUE_BUTTON_ID = "org.wikipedia:id/fragment_onboarding_forward_button";
    public static final String NEW_WAYS_ID = "org.wikipedia:id/primaryTextView";
    public static final String SKIP_BUTTON_ID = "org.wikipedia:id/fragment_onboarding_skip_button";
    public static final String HOMEPAGE_SEARCH_FIELD_XPATH = "//*[contains(@text, 'Search Wikipedia')]";
    public static final String SEARCH_FIELD_ID = "org.wikipedia:id/search_src_text";
    public static final String SEARCH_RESULTS_AREA_ID = "org.wikipedia:id/search_results_list";
    public static final String CLEAR_SEARCH_BUTTON_ID = "org.wikipedia:id/search_close_btn";
    public static final String BACK_TO_HOME_BUTTON_XPATH = "//android.view.ViewGroup[@resource-id=\"org.wikipedia:id/search_toolbar\"]//android.widget.ImageButton";
    public static final String RESULTS_TITLE_ID = "org.wikipedia:id/page_list_item_title";
    public static final String HEADER_IMAGE_ID = "org.wikipedia:id/view_page_header_image";
    public static final String SAVE_BUTTON_ID = "org.wikipedia:id/article_menu_bookmark";
    public static final String BACK_TO_SEARCH_BUTTON_XPATH = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    public static final String SAVED_BUTTON_XPATH = "//android.widget.FrameLayout[@content-desc=\"Saved\"]";
    public static final String SAVED_PUBLICATIONS_FOLDER_ID = "org.wikipedia:id/recycler_view";
    public static final String SAVED_PUBLICATIONS_LIST_XPATH = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/reading_list_recycler_view\"]/android.view.ViewGroup";
    public static final String FIRST_SAVED_PUBLICATION_XPATH = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"org.wikipedia:id/reading_list_recycler_view\"]/android.view.ViewGroup[2]";
    public static final String REMOVE_BUTTON_ID = "org.wikipedia:id/reading_list_item_remove";
    public static final String NOTIFICATION_TEXT_ID = "org.wikipedia:id/snackbar_text";
    public static final String SAVED_PUBLICATION_TITLE_ID = "org.wikipedia:id/page_list_item_title";
    public static final String PUBLICATION_TITLE_XPATH = "//android.view.View[@resource-id=\"pcs\"]/android.view.View[1]/android.view.View[1]";


    //Texts
    public static final String NEW_WAYS_TEXT = "New ways to explore";
    public static final String SEARCH_FIELD_PLACEHOLDER = "Search Wikipedia";
    public static final String SEARCH_WORD = "JAVA";
    public static final String PUBLICATION_TITLE_TEXT = "JavaScript";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "TestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\AppiumTraningProject\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.closeApp();
        driver.quit();
    }

//    @Test
//    public void firstTest() {
//        WebElement continueButton =
//                waitForElementPresented(By.id(CONTINUE_BUTTON_ID), 3, "Continue button is missing");
//        continueButton.click();
//        WebElement newWays =
//                waitForElementPresented(By.id(NEW_WAYS_ID), 5, "New ways text is missing");
//        assertElementHasText(newWays, NEW_WAYS_TEXT, "New ways text is incorrect");
//        skipIntro();
//        WebElement homepageSearchField =
//                waitForElementPresented(By.xpath(HOMEPAGE_SEARCH_FIELD_XPATH),
//                        5, "Search field is missing in the HomePage");
//        assertElementHasText(homepageSearchField, SEARCH_FIELD_PLACEHOLDER,
//                "Homepage Search field text is incorrect");
//        homepageSearchField.click();
//        WebElement searchField =
//                waitForElementPresented(By.id(SEARCH_FIELD_ID), 5, "Search field is missing");
//        assertElementHasText(searchField, SEARCH_FIELD_PLACEHOLDER, "Search field text is incorrect");
//    }
//
//    @Test
//    public void secondTest() {
//        skipIntro();
//        WebElement homepageSearchField =
//                waitForElementPresented(By.xpath(HOMEPAGE_SEARCH_FIELD_XPATH),
//                        5, "Search field is missing in the HomePage");
//        homepageSearchField.click();
//        WebElement searchField =
//                waitForElementPresented(By.id(SEARCH_FIELD_ID), 5, "Search field is missing");
//        searchField.sendKeys("Java");
//        WebElement searchResultsArea =
//                waitForElementPresented(By.id(SEARCH_RESULTS_AREA_ID), 5, "Search result is empty");
//        WebElement searchCloseBtn =
//                waitForElementPresented(By.id(CLEAR_SEARCH_BUTTON_ID), 5, "Close button is missing");
//        searchCloseBtn.click();
//        Assert.assertFalse("Search not canceled", searchResultsArea.isDisplayed());
//    }

//    @Test
//    public void thirdTest() {
//        skipIntro();
//        searchWord(SEARCH_WORD);
//        waitForElementsPresented(By.id(RESULTS_TITLE_ID), 10, "Search result is empty");
//        List<WebElement> resultsList = driver.findElements(By.id(RESULTS_TITLE_ID));
//        for (WebElement result : resultsList) {
//            Assert.assertTrue("Search Result is incorrect",
//                    result.getText().toLowerCase().contains(SEARCH_WORD.toLowerCase()));
//        }
//    }

    @Test
    public void testSavePublicationsAndRemove() {
        skipIntro();
        searchWord(SEARCH_WORD);
        waitForElementsPresented(By.id(RESULTS_TITLE_ID), 10, "Search result is empty");
        List<WebElement> resultsList = driver.findElements(By.id(RESULTS_TITLE_ID));
        Assert.assertTrue("Search result is empty", resultsList.size() > 0);
        resultsList.get(1).click();
        waitForElementPresented(By.id(HEADER_IMAGE_ID), 10, "Header image is not shown");
        WebElement saveButton = waitForElementPresented(By.id(SAVE_BUTTON_ID), 10, "Save button is missing");
        saveButton.click();
        WebElement backToSearch = waitForElementPresented(By.xpath(BACK_TO_SEARCH_BUTTON_XPATH), 10,
                "Back to search arrow-button is missing");
        backToSearch.click();
        resultsList = driver.findElements(By.id(RESULTS_TITLE_ID));
        resultsList.get(2).click();
        waitForElementPresented(By.id(HEADER_IMAGE_ID), 10, "Header image is not shown");
        saveButton = waitForElementPresented(By.id(SAVE_BUTTON_ID), 10, "Save button is missing");
        saveButton.click();
        backToSearch = waitForElementPresented(By.xpath(BACK_TO_SEARCH_BUTTON_XPATH), 10,
                "Back to search arrow-button is missing");
        backToSearch.click();
        WebElement backToHome = waitForElementPresented(By.xpath(BACK_TO_HOME_BUTTON_XPATH), 10,
                "Back to home button is missing");
        backToHome.click();
        WebElement savedPublicationsPageButton = waitForElementPresented(By.xpath(SAVED_BUTTON_XPATH), 10,
                "Saved button is missing");
        savedPublicationsPageButton.click();
        WebElement savedPublicationsFolder = waitForElementPresented(By.id(SAVED_PUBLICATIONS_FOLDER_ID), 10,
                "Saved publications folder is missing");
        savedPublicationsFolder.click();
        List<WebElement> savedPublications = waitForElementsPresented(By.xpath(SAVED_PUBLICATIONS_LIST_XPATH), 10,
                "Saved publications are missing");
        Assert.assertTrue("", savedPublications.size() > 2);
//        swipeElementToLeft(savedPublications.get(1));
        deleteFirstSavedPublication();
        waitForElementPresented(By.id(NOTIFICATION_TEXT_ID), 10, "Notification is missing");
        savedPublications = waitForElementsPresented(By.xpath(SAVED_PUBLICATIONS_LIST_XPATH), 10,
                "Saved publications are missing");
        Assert.assertEquals("Publication is not deleted", 2, savedPublications.size());
        WebElement savedPublication = waitForElementPresented(By.id(SAVED_PUBLICATION_TITLE_ID), 10,
                "Publication title is missing from folder");
        String savedPublicationTitle = savedPublication.getText();
        savedPublication.click();
        String publicationTitle = waitForElementPresented(By.xpath(PUBLICATION_TITLE_XPATH), 10,
                "Publication title is not shown in the publication").getText();
        Assert.assertEquals("Titles aren't equals", savedPublicationTitle, publicationTitle);
    }


    //Wait methods
    private WebElement waitForElementPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitForElementsPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresented(by,timeoutInSeconds, errorMessage);
        element.clear();
        return element;
    }


    //Assert method
    private void assertElementHasText(WebElement element, String expectedText, String errorMessage) {
        try {
            String actualText = element.getText();
            Assert.assertEquals(errorMessage, expectedText, actualText);
        } catch (Exception ex){
            System.out.println("Element not found");
        }
    }


    // Help methods
    private void skipIntro() {
        WebElement skipButton =
                waitForElementPresented(By.id(SKIP_BUTTON_ID), 10, "SKIP button is missing");
        skipButton.click();
    }

    private void searchWord(String word) {
        WebElement homepageSearchField =
                waitForElementPresented(By.xpath(HOMEPAGE_SEARCH_FIELD_XPATH),
                        5, "Search field is missing in the HomePage");
        homepageSearchField.click();
        WebElement searchField =
                waitForElementPresented(By.id(SEARCH_FIELD_ID), 5, "Search field is missing");
        searchField.sendKeys(word);
        waitForElementPresented(By.id(SEARCH_RESULTS_AREA_ID), 5, "Search result is empty");
    }

    private void swipeUp(int timeOfSwipe) {
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

    private void quickSwipeUp() {
        swipeUp(200);
    }

    private void swipeUpToElement(By by, int maxSwipes, String errorMessage) {
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

    private void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresented(by, 10, errorMessage);
        swipeElementToLeft(element);
    }

    private void swipeElementToLeft(WebElement element) {
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

    private void deleteFirstSavedPublication() {
        WebElement firstPublication = waitForElementPresented(By.xpath(FIRST_SAVED_PUBLICATION_XPATH), 10, "Some Error");

        TouchAction action = new TouchAction(driver);
        action.longPress(firstPublication);
        action.perform();

        quickSwipeUp();
        WebElement removeButton = waitForElementPresented(By.id(REMOVE_BUTTON_ID), 10, "Remove button is not shown");
        removeButton.click();

    }

}
