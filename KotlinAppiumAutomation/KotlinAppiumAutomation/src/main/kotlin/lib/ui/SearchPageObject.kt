package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

abstract class SearchPageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {


    abstract val SEARCH_INIT_ELEMENT: String
    abstract val SEARCH_INPUT : String
    abstract val SEARCH_RESULT_BY_SUBSTRING_TPL: String
    abstract val SEARCH_RESULT_CONTAINER: String
    abstract val SEARCH_CANCEL_BUTTON: String
    open var CLOSE_SEARCH_SCREEN = ""
    abstract val SEARCH_RESULT_LIST: String
    abstract val SEARCH_EMPTY_RESULT_ELEMENT: String

    fun initSearchInput() {
        this.waitForElementPresent(
            SEARCH_INIT_ELEMENT,
            "Cannot find search input after clicking search init element",
            10
        )
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 10)
    }

    //TEMPLATES METHODS

    fun getResultSearchElement(substring: String): String {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring)
    }

    //TEMPLATES METHODS

    fun typeSearchLine(search_line: String) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 10)
    }

    fun waitForSearchResult(substring: String) {
        val search_result_xpath = getResultSearchElement(substring)
        this.waitForElementPresent(
            search_result_xpath,
            "Cannot find search result with substring $substring", 10
        )
    }

    fun clickByArticleWithSubstring(substring: String) {
        val search_result_xpath = getResultSearchElement(substring)
        this.waitForElementAndClick(
            search_result_xpath,
            "Cannot find search result with substring $substring", 10
        )
    }

    fun searchResultContainerIsPresent() {
        this.waitForElementPresent(SEARCH_RESULT_CONTAINER, "Cannot find search result container", 10)
    }

    fun searchResultContainerIsNotPresent() {
        this.waitForElementNotPresent(SEARCH_RESULT_CONTAINER, "Search result container is present", 10)
    }

    fun clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 10)
    }

    fun clickCloseSearchScreen(){
        this.waitForElementAndClick(CLOSE_SEARCH_SCREEN, "Cannot find and click close search button", 10)
    }

    fun getAmountOfFoundArticles(): Int {
        this.waitForElementPresent(SEARCH_RESULT_LIST, "CAnnot find anything by the request", 15)
        return this.getAmountOfElements(SEARCH_RESULT_LIST)
    }
}
