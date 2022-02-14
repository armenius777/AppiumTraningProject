import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.By;
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
    public static final String RESULTS_TITLE_ID = "org.wikipedia:id/page_list_item_title";

    //Texts
    public static final String NEW_WAYS_TEXT = "New ways to explore";
    public static final String SEARCH_FIELD_PLACEHOLDER = "Search Wikipedia";
    public static final String SEARCH_WORD = "JAVA";

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

    @Test
    public void firstTest() {
        WebElement continueButton =
                waitForElementPresented(By.id(CONTINUE_BUTTON_ID), 3, "Continue button is missing");
        continueButton.click();
        WebElement newWays =
                waitForElementPresented(By.id(NEW_WAYS_ID), 5, "New ways text is missing");
        assertElementHasText(newWays, NEW_WAYS_TEXT, "New ways text is incorrect");
        skipIntro();
        WebElement homepageSearchField =
                waitForElementPresented(By.xpath(HOMEPAGE_SEARCH_FIELD_XPATH),
                        5, "Search field is missing in the HomePage");
        assertElementHasText(homepageSearchField, SEARCH_FIELD_PLACEHOLDER,
                "Homepage Search field text is incorrect");
        homepageSearchField.click();
        WebElement searchField =
                waitForElementPresented(By.id(SEARCH_FIELD_ID), 5, "Search field is missing");
        assertElementHasText(searchField, SEARCH_FIELD_PLACEHOLDER, "Search field text is incorrect");
    }

    @Test
    public void secondTest() {
        skipIntro();
        WebElement homepageSearchField =
                waitForElementPresented(By.xpath(HOMEPAGE_SEARCH_FIELD_XPATH),
                        5, "Search field is missing in the HomePage");
        homepageSearchField.click();
        WebElement searchField =
                waitForElementPresented(By.id(SEARCH_FIELD_ID), 5, "Search field is missing");
        searchField.sendKeys("Java");
        WebElement searchResultsArea =
                waitForElementPresented(By.id(SEARCH_RESULTS_AREA_ID), 5, "Search result is empty");
        WebElement searchCloseBtn =
                waitForElementPresented(By.id(CLEAR_SEARCH_BUTTON_ID), 5, "Close button is missing");
        searchCloseBtn.click();
        Assert.assertFalse("Search not canceled", searchResultsArea.isDisplayed());
    }

    @Test
    public void thirdTest() {
        skipIntro();
        searchWord(SEARCH_WORD);
        waitForElementsPresented(By.id(RESULTS_TITLE_ID), 10, "Search result is empty");
        List<WebElement> resultsList = driver.findElements(By.id(RESULTS_TITLE_ID));
        for (WebElement result : resultsList) {
            Assert.assertTrue("Search Result is incorrect",
                    result.getText().toLowerCase().contains(SEARCH_WORD.toLowerCase()));
        }
    }


    //Wait methods
    private WebElement waitForElementPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitForElementsPresented(By by, long timeout, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
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

}
