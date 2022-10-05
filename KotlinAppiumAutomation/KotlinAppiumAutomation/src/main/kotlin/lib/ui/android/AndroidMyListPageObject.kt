package lib.ui.android

import io.appium.java_client.AppiumDriver
import lib.ui.ArticlePageObject
import lib.ui.MyListPageObject
import org.openqa.selenium.WebElement

class AndroidMyListPageObject(driver: AppiumDriver<WebElement>): MyListPageObject(driver) {
    override var FOLDER_MY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']"
    override var ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']"
    override var FIRST_ARTICLE_ON_MY_LIST_SCREEN =
        "xpath://*[@resource-id='org.wikipedia:id/reading_list_contents']/android.widget.FrameLayout[2]//android.widget.TextView[1]"

}