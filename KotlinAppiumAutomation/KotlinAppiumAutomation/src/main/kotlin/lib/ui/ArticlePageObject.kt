package lib.ui

import io.appium.java_client.AppiumDriver
import lib.Platform
import org.openqa.selenium.WebElement


abstract class ArticlePageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    abstract val TITLE: String
    open var TITLE_IOS_FOR_ASSERT = ""
    abstract val FOOTER_ELEMENT: String
    open var OPTIONS_BUTTON: String = ""
    abstract val OPTIONS_ADD_TO_MY_LIST_BUTTON: String
    open var ADD_TO_MY_LIST_OVERLAY: String = ""
    open var MY_LIST_NAME_INPUT: String = ""
    open var MY_LIST_OK_BUTTON: String = ""
    abstract val CLOSE_ARTICLE_BUTTON: String
    open var MY_READING_LIST_TPL: String = ""


    fun waitForTitleElement(): WebElement {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 10)
    }

    fun getArticleName(article_title: String): String {
        return TITLE_IOS_FOR_ASSERT.replace("{TITLE}", article_title)
    }

    fun titleIsPresent(article_title: String){
        val first_article_xpath = getArticleName(article_title)
        this.waitForElementPresent(first_article_xpath,"Article not present",10)
    }

    fun getArticleTitle(): String {
        val title_element: WebElement = waitForTitleElement()
        return if (Platform.getInstance().isAndroid())
            title_element.text
        else
            title_element.tagName
    }

    fun swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find element", 40)
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40)
        }
    }

    fun addArticleToMyNewList(name_of_folder: String) {

        this.waitForElementAndClick(OPTIONS_BUTTON, "Options button not found", 10)
        //Тапаем сохранить в список для чтения
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Button add to read list not found", 10)
        //Проходим онбординг
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Button 'GOT IT' not found", 10)
        //Стираем дефолтное название списка
        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "Input field not found", 10)
        //Вводим название списка из переменной readListName
        this.waitForElementAndSendKeys(
            MY_LIST_NAME_INPUT,
            name_of_folder,
            "Cannot put text into articles folder input",
            10
        )
        //Тапаем ОК на алерте
        this.waitForElementAndClick(
            MY_LIST_OK_BUTTON,
            "Cannot press OK button",
            10
        )
    }

    fun getMyListXpathByName(name_of_folder: String): String {
        return MY_READING_LIST_TPL.replace("{MY_READING_LIST_NAME}", name_of_folder)
    }

    fun addArticleToExistList(name_of_folder: String) {
        val name_of_folder = getMyListXpathByName(name_of_folder)
        this.waitForElementAndClick(OPTIONS_BUTTON, "Options button not found", 10)
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Button add to read list not found", 10)
        this.waitForElementAndClick(name_of_folder, "Reading list not found", 10)
    }

    fun closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Button close not found", 10)
    }

    fun checkTitlePresentWithoutTimeout() {
        assertElementPresentWithoutTimeout(TITLE, "title not found")
    }

    fun addArticlesToMySaved() {
        waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 10)
    }
}
