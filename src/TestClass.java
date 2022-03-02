import com.sun.org.glassfish.gmbal.Description;
import lib.CoreTestCase;
import lib.Locators;
import lib.ui.*;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TestClass extends CoreTestCase {

    private MainPageObject mainPageObject;
    private HomePage homePage;
    private SearchPage searchPage;
    private PostPage postPage;
    private PublicationsPage publicationsPage;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        postPage = new PostPage(driver);
        publicationsPage = new PublicationsPage(driver);
    }


    @Test
    @Description("Verify that search page opens after click on search field")
    public void testCheckSearchFieldWithContinue() {
        homePage.clickOnContinue();
        WebElement newWays = homePage.waitForNewWaysShown();
        mainPageObject.assertElementHasText(newWays, Constants.NEW_WAYS_TEXT, "New ways text is incorrect");
        homePage.skipIntro();
        homePage.clickOnHomepageSearchField();
        WebElement searchField =
                mainPageObject.waitForElementPresented(By.id(Locators.SEARCH_FIELD_ID),
                        5, "Search field is missing");
        mainPageObject.assertElementHasText(searchField, Constants.SEARCH_FIELD_PLACEHOLDER,
                "Search field text is incorrect");
    }

    @Test
    @Description("Verify that user can cancel search")
    public void testCheckSearchCancel() {
        homePage.skipIntro();
        homePage.clickOnHomepageSearchField();
        searchPage.searchWord(Constants.SEARCH_WORD);
        WebElement searchResultsArea =
                mainPageObject.waitForElementPresented(By.id(Locators.SEARCH_RESULTS_AREA_ID),
                        5, "Search result is empty");
        WebElement searchCloseBtn =
                mainPageObject.waitForElementPresented(By.id(Locators.CLEAR_SEARCH_BUTTON_ID),
                        5, "Close button is missing");
        searchCloseBtn.click();
        Assert.assertFalse("Search not canceled", searchResultsArea.isDisplayed());
    }

    @Test
    @Description("Verify that search results shown correct")
    public void testCheckSearch() {
        homePage.skipIntro();
        searchPage.searchWord(Constants.SEARCH_WORD);
        mainPageObject.waitForElementsPresented(By.id(Locators.RESULTS_TITLE_ID),
                10, "Search result is empty");
        List<WebElement> resultsList = searchPage.getSearchResults();
        for (WebElement result : resultsList) {
            Assert.assertTrue("Search Result is incorrect",
                    result.getText().toLowerCase().contains(Constants.SEARCH_WORD.toLowerCase()));
        }
    }

    @Test
    @Description("Ex5: Verify that user can save two publications and remove one from that")
    public void testSavePublicationsAndRemove() {
        homePage.skipIntro();
        searchPage.searchWord(Constants.SEARCH_WORD);
        mainPageObject.waitForElementsPresented(By.id(Locators.RESULTS_TITLE_ID),
                10, "Search result is empty");
        List<WebElement> resultsList = searchPage.getSearchResults();
        Assert.assertTrue("Search result is empty", resultsList.size() > 0);
        resultsList.get(1).click();
        postPage.savePost();
        postPage.backToSearchResults();
        resultsList = searchPage.getSearchResults();
        resultsList.get(2).click();
        postPage.savePost();
        postPage.backToSearchResults();
        searchPage.backToHome();
        homePage.goToSavedPublicationsFolder();
        List<WebElement> savedPublications = publicationsPage.getSavedPublications();
        Assert.assertTrue("Saved publications size is incorrect", savedPublications.size() > 2);
//        swipeElementToLeft(savedPublications.get(1));
        publicationsPage.deleteFirstSavedPublication();
        mainPageObject.waitForElementPresented(By.id(Locators.NOTIFICATION_TEXT_ID), 10,
                "Notification is missing");
        savedPublications = publicationsPage.getSavedPublications();
        Assert.assertEquals("Publication is not deleted", 2, savedPublications.size());
        WebElement savedPublication = mainPageObject.waitForElementPresented(By.id(Locators.SAVED_PUBLICATION_TITLE_ID),
                10, "Publication title is missing from folder");
        String savedPublicationTitle = savedPublication.getText();
        savedPublication.click();
        String publicationTitle = mainPageObject.waitForElementPresented(By.xpath(Locators.PUBLICATION_TITLE_XPATH),
                10, "Publication title is not shown in the publication").getText();
        Assert.assertEquals("Titles aren't equals", savedPublicationTitle, publicationTitle);
    }

    @Test
    @Description("Ex6: Тест: assert title")
    public void testCheckElementPresentedWithoutWait() {
        homePage.skipIntro();
        driver.rotate(ScreenOrientation.LANDSCAPE); //добавлен поворот для задачи 7.
        searchPage.searchWord(Constants.SEARCH_WORD);
        mainPageObject.waitForElementsPresented(By.id(Locators.RESULTS_TITLE_ID), 10,
                "Search result is empty");
        List<WebElement> resultsList = searchPage.getSearchResults();
        Assert.assertTrue("Search result is empty", resultsList.size() > 0);
        resultsList.get(1).click();
        Assert.assertTrue("Publication title is missing",
                mainPageObject.assertElementPresent(By.xpath(Locators.PUBLICATION_TITLE_XPATH)));
    }

}
