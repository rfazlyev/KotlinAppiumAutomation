package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

open class ArticlePageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    private val TITLE = "id:org.wikipedia:id/view_page_title_text"
    private val FOOTER_ELEMENT = "xpath://*[@text='View page in browser']"
    private val OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc=\"More options\"]"
    private val OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']"
    private val ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button"
    private val MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input"
    private val MY_LIST_OK_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/buttonPanel']//*[@text='OK']"
    private val CLOSE_ARTICLE_BUTTON =
        "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']//*[@content-desc='Navigate up']"
    private val MY_READING_LIST_TPL = "xpath://*[@text='{MY_READING_LIST_NAME}']"

    fun waitForTitleElement(): WebElement {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 10)
    }

    fun getArticleTitle(): String {
        val title_element: WebElement = waitForTitleElement()
        return title_element.text
    }

    fun swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find element", 20)
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
}
