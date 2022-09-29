package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

abstract class MyListPageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    open var FOLDER_MY_NAME_TPL = ""
    open var ARTICLE_BY_TITLE_TPL = ""
    open var FIRST_ARTICLE_ON_MY_LIST_SCREEN = ""
    open var BUTTON_CLOSE_SYNC_MY_ARTICLES = ""
    open var FIRST_ARTICLE_IOS = ""
    open var BUTTON_DELETE_ARTICLE = ""
    open var FIRST_ARTICLE_IOS_TO_DELETE = ""


    fun getFolderXpathByName(name_of_folder: String): String {
        return FOLDER_MY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder)
    }

    fun getSavedArticleXpathByTitle(article_title: String): String {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title)
    }

    fun getFirstArticleName(article_title: String): String {
        return FIRST_ARTICLE_IOS.replace("{TITLE}", article_title)
    }

    fun articleIsPresent(article_title: String){
        val first_article_xpath = getFirstArticleName(article_title)
        this.waitForElementPresent(first_article_xpath,"Article not present",10)
    }

    fun swipeAndDeleteFirstArticleIos() {
        this.swipeElementToLeft(FIRST_ARTICLE_IOS_TO_DELETE, "Cannot find first article")
        this.waitForElementAndClick(BUTTON_DELETE_ARTICLE, "Cannot delete first article", 10)
    }

    fun openFirstArticleIos() {
        this.waitForElementAndClick(FIRST_ARTICLE_IOS_TO_DELETE, "Cannot open first article",10)
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

    fun clickButtonCloseSyncMyArticlesWindow() {
        this.waitForElementAndClick(
            BUTTON_CLOSE_SYNC_MY_ARTICLES,
            "Cannot find and click button close sync articles window)",
            10
        )
    }
}

