package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class home_work extends CoreTestCase {

    @Test
    public void testCheckSearchResult()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.waitForSearchResult("Wikimedia list article");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    public void testAssertElementPresent()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_after = ArticlePageObject.getArticleTitleWithoutWait();


        assertEquals(
                "Title is not found",
                "Java (programming language)",
                title_after);

    }

    @Test
    public void testSaveTwoArticleToMyList() throws InterruptedException {
        String name_of_folder = "Learning programming";
        String article2_description = "Object-oriented programming language";
        String article1_description = "Wikimedia list article";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addArticleToTheFolderNoFoldersBefore(article1_description, name_of_folder);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.closeArticle();
        //MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
        //        "Cannot close article, cannot find X link",
        //        5);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        ArticlePageObject.addArticleToTheFolder(article2_description, name_of_folder);
        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.closeArticle();
        //MainPageObject.waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
        //        "Cannot close article, cannot find X link",
        //        5);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(second_article_title);

        // MainPageObject.waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
        //        "Cannot find navigation button to My List",
        //        5);

        // MainPageObject.waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder +"']"),
        //        "Cannot find navigation button to My List",
        //        5);

        // MainPageObject.swipeElementToLeft(
        //        By.xpath("//android.widget.TextView[@text='Java version history']"),
        //        //By.xpath("//*[@text='Java (programming language)']"),
        //        "Cannot find saved article");
        // ---
        //MainPageObject.waitForElementNotPresent(By.xpath("//*[@text='Java version history']"),
        //        "Cannot delete saved article",
        //        10);

        // String title_before = MainPageObject.waitForElementAndGetAttribute(
        //        By.id("org.wikipedia:id/page_list_item_title"),
        //        "text",
        //        "Cannot find title of article",
        //        15);

        //MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"),
        //        "Cannot find saved article, after removing other article",
        //        10);

        //String title_after = MainPageObject.waitForElementAndGetAttribute(
        //        By.id("org.wikipedia:id/view_page_title_text"),
        //        "text",
        //        "Cannotfind title of article",
        //        15);

        //assertEquals(
        //        "Article title have been changed after screen rotation",
        //        title_before,
        //        title_after);

    }


}
