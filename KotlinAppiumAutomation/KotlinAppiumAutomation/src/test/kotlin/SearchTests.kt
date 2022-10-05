import lib.CoreTestCase
import lib.ui.SearchPageObject
import lib.ui.factories.SearchPageObjectFactory
import org.junit.Assert
import org.junit.Test

class SearchTests : CoreTestCase() {

    @Test
    fun testCancelSearch() {

        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.searchResultContainerIsPresent()
        SearchPageObject.clickCancelSearch()
        SearchPageObject.searchResultContainerIsNotPresent()
    }

    @Test
    fun testSearch() {
        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.waitForSearchResult("Object-oriented programming language")
    }

    @Test
    fun testAmountOfNotEmptySearch() {
        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        SearchPageObject.initSearchInput()
        val search_line = "Linkin Park Discography"
        SearchPageObject.typeSearchLine(search_line)
        val amount_of_search_results = SearchPageObject.getAmountOfFoundArticles()
        Assert.assertTrue("We found too few results", amount_of_search_results > 0)
    }
}
