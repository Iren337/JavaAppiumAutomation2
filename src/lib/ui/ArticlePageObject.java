package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private  static final  String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMWNT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            CREATED_FOLDER_TPL = "//android.widget.TextView[@text='{SUBSTRING}']";



    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement  waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return  title_element.getAttribute("text");
    }

    public String getArticleTitleWithoutWait()
    {
        String title_element = assertElementPresent(By.id(TITLE), "text");
        return  title_element;
    }

    public void swipeToFooter(){
        this.swipeUpQToFindElement(
                By.xpath(FOOTER_ELEMWNT), "Cannot find the end of article", 20
        );
    }

    public void addArticleToMyList(String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                15);

        MainPageObject MainPageObject = new MainPageObject(driver);


        MainPageObject.waitForElementAndClickWithDelay(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
                "Cannot find option to add article to reading list",
                5, 4);

        // this.waitForElementAndClick(
        //        By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
        //        "Cannot find option to add article to reading list",
        //        5);

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5);


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5);
    }

    private static String getCreatedFolder(String substring)
    {
        return CREATED_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }

    public void addAtricleToMyListToExistingFolder(String name_of_folder) throws InterruptedException {
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                15);

        MainPageObject MainPageObject = new MainPageObject(driver);


        MainPageObject.waitForElementAndClickWithDelay(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
                "Cannot find option to add article to reading list",
                5, 4);

        // this.waitForElementAndClick(
        //        By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON ),
        //        "Cannot find option to add article to reading list",
        //        5);

        this.waitForElementAndClick(By.xpath(getCreatedFolder(name_of_folder)),
                "Cannot find folder with name " + name_of_folder,
                15);

    }

    public void addArticleToTheFolderNoFoldersBefore(String article_title_description, String name_of_folder) throws InterruptedException {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

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
