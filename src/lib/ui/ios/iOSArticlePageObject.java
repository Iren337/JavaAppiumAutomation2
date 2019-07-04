package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMWNT = "id:View article in browser";
        //OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        //ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        //MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        //MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        //CREATED_FOLDER_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
    }

    public iOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
