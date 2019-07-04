package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMWNT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CREATED_FOLDER_TPL;



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement  waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 25);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid())
        {
            return  title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }

    public String getArticleTitleWithoutWait()
    {
        String title_element = assertElementPresent(TITLE, "text");
        return  title_element;
    }

    public void swipeToFooter(){

        if(Platform.getInstance().isAndroid()){
            this.swipeUpQToFindElement(
                    FOOTER_ELEMWNT, "Cannot find the end of article", 40
            );
        } else {
            this.swipeUpTitleElementAppear(FOOTER_ELEMWNT, "Cannot find the end of article", 40);
        }

    }

    public void addArticleToMyList(String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                15);

        MainPageObject MainPageObject = new MainPageObject(driver);


        MainPageObject.waitForElementAndClickWithDelay(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5, 4);

        // this.waitForElementAndClick(
        //        By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
        //        "Cannot find option to add article to reading list",
        //        5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5);


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5);
    }

    private static String getCreatedFolder(String substring)
    {
        return CREATED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }

    public void addAtricleToMyListToExistingFolder(String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                15);

        MainPageObject MainPageObject = new MainPageObject(driver);


        MainPageObject.waitForElementAndClickWithDelay(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5, 4);

        // this.waitForElementAndClick(
        //        By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
        //        "Cannot find option to add article to reading list",
        //        5);

        this.waitForElementAndClick(getCreatedFolder(name_of_folder),
                "Cannot find folder with name " + name_of_folder,
                15);

    }

    public void addArticleToTheFolderNoFoldersBefore(String article_title_description, String name_of_folder) throws InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickByArticleWithSubstring(article_title_description);
        //waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_title_description + "']"),
        //        "Cannot find Search Wikipedia input", 5);

        this.waitForTitleElement();
        String article_title = this.getArticleTitle();

        this.addArticleToMyList(name_of_folder);
        //waitForElementPresent(
        //        By.id("org.wikipedia:id/view_page_title_text"),
        //        "cannot find article title",
        //        15
        // );

        // waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
        //        "Cannot find button to open article options",
        //        15);

        // waitForElementAndClickWithDelay(
        //        By.xpath("//*[@text='Add to reading list']"),
        //        "Cannot find option to add article to reading list",
        //        8,
        //        3
        // );


        // waitForElementAndClick(
        //        By.id("org.wikipedia:id/onboarding_button"),
        //        "Cannot find 'Got it' tip overlay",
        //        5);

        // waitForElementAndClear(
        //        By.id("org.wikipedia:id/text_input"),
        //        "Cannot find input to set name of articles folder",
        //        5);

        // waitForElementAndSendKeys(
        //        By.id("org.wikipedia:id/text_input"),
        //        name_of_folder,
        //        "Cannot put text into articles folder input",
        //        5);

        //waitForElementAndClick(
        //        By.xpath("//*[@text='OK']"),
        //        "Cannot press OK button",
        //        5);

    }

    public void addArticleToTheFolder(String article_title_description, String name_of_folder) throws InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickByArticleWithSubstring(article_title_description);
        //waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_title_description + "']"),
        //        "Cannot find Search Wikipedia input", 5);

        this.waitForTitleElement();
        String article_title = this.getArticleTitle();

        //waitForElementPresent(
        //        By.id("org.wikipedia:id/view_page_title_text"),
        //        "cannot find article title",
        //        15
        //);

        //waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
        //        "Cannot find button to open article options",
        //        15);

        //waitForElementAndClickWithDelay(
        //        By.xpath("//*[@text='Add to reading list']"),
        //        "Cannot find option to add article to reading list",
        //        8,
        //        15
        // );

        // waitForElementAndClick(
        //        By.xpath("//android.widget.TextView[@text='" + name_of_folder +"']"),
        //        "Cannot find folder with name " + name_of_folder,
        //        15
        // );
        this.addAtricleToMyListToExistingFolder(name_of_folder);
    }

}
