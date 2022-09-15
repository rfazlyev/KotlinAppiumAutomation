package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class MyListPageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    private val FOLDER_MY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']"
    private val ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']"
    private val FIRST_ARTICLE_ON_MY_LIST_SCREEN =
        "xpath://*[@resource-id='org.wikipedia:id/reading_list_contents']/android.widget.FrameLayout[2]//android.widget.TextView[1]"


    fun getFolderXpathByName(name_of_folder: String): String {
        return FOLDER_MY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder)
    }

    fun getSavedArticleXpathByTitle(article_title: String): String {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title)
    }

    fun openFolderByName(name_of_folder: String) {
        val folder_name_xpath = getFolderXpathByName(name_of_folder)
        this.waitForElementAndClick(folder_name_xpath, "Cannot find folder by name $name_of_folder", 10)
    }

    fun waitForArticleToDisappearByTitle(article_title: String) {
        val article_xpath = getSavedArticleXpathByTitle(article_title)
        this.waitForElementNotPresent(
            article_xpath,
            "Saved article still present with title $article_title",
            10
        )
    }

    fun waitForArticleToAppearByTitle(article_title: String) {
        val article_xpath = getSavedArticleXpathByTitle(article_title)
        this.waitForElementPresent(article_xpath, "Cannot find article by title $article_title", 10)
    }

    fun swipeByArticleToDelete(article_title: String) {
        waitForArticleToAppearByTitle(article_title)
        val article_xpath = getSavedArticleXpathByTitle(article_title)
        this.swipeElementToLeft(article_xpath, "Cannot saved first article")
        this.waitForArticleToDisappearByTitle(article_title)
    }

    fun waitForTitleElementFromMyListScreen(): WebElement {
        return this.waitForElementPresent(
            FIRST_ARTICLE_ON_MY_LIST_SCREEN,
            "Cannot find article title on page",
            10
        )
    }

    fun getArticleTitleFromMyListScreen(): String {
        val title_element: WebElement = waitForTitleElementFromMyListScreen()
        return title_element.text
    }

    fun openFirstArticleFromMyListScreen() {
        this.waitForElementAndClick(
            FIRST_ARTICLE_ON_MY_LIST_SCREEN,
            "Cannot find and click first article",
            10
        )
    }
}
