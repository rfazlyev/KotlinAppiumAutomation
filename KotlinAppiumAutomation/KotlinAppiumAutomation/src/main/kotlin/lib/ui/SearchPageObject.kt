package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

open class SearchPageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {


    private val SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container"
    private val SEARCH_INPUT = "id:org.wikipedia:id/search_src_text"
    private val SEARCH_RESULT_BY_SUBSTRING_TPL =
        "xpath://*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='{SUBSTRING}']"
    private val SEARCH_RESULT_CONTAINER = "id:org.wikipedia:id/search_results_container"
    private val SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn"
    private val SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list"

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

    fun getAmountOfFoundArticles(): Int {
        this.waitForElementPresent(SEARCH_RESULT_LIST, "CAnnot find anything by the request", 15)
        return this.getAmountOfElements(SEARCH_RESULT_LIST)
    }
}
