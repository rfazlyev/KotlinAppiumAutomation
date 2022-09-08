import lib.CoreTestCase
import lib.ui.*
import org.junit.Assert
import org.junit.Test

class FirstTest : CoreTestCase() {

    //Задание Ex3
    @Test
    fun testCancelSearch() {

        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.searchResultContainerIsPresent()
        SearchPageObject.clickCancelSearch()
        SearchPageObject.searchResultContainerIsNotPresent()
    }

    // Задание Ex5
    @Test
    fun testSaveTwoArticles() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObject(driver)
        val NavigationUI: NavigationUI = NavigationUI(driver)
        val MyListPageObject:MyListPageObject = MyListPageObject(driver)

        //Тапаем на поле ввода
        SearchPageObject.initSearchInput()
        //Вводим текст
        SearchPageObject.typeSearchLine("Java")
        //Тапаем по первой статье из саджеста
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language")
        ArticlePageObject.waitForTitleElement()
        //Получаем название статьи
        val article_title = ArticlePageObject.getArticleTitle()
        val name_of_folder = "Learning programming"
        //Добавляем статью в новый список
        ArticlePageObject.addArticleToMyNewList(name_of_folder)
        //Закрываем статью
        ArticlePageObject.closeArticle()
        //Тапаем на поле ввода
        SearchPageObject.initSearchInput()
        //Вводим текст
        SearchPageObject.typeSearchLine("Java")
        //Тапаем по другой статье
        SearchPageObject.clickByArticleWithSubstring("High-level programming language")
        //Сохраняем ее в тот же список
        ArticlePageObject.addArticleToExistList(name_of_folder)
        //Закрываем статью
        ArticlePageObject.closeArticle()
        //Открываем экран списки для чтения
        NavigationUI.clickMyList()
        //Тапаем по нашему списку
        MyListPageObject.openFolderByName(name_of_folder)
        //Удаляем одну статью
        MyListPageObject.swipeByArticleToDelete(article_title)
        //Сохраняем название статьи с экрана списка для чтения
        MyListPageObject.waitForTitleElementFromMyListScreen()
        val titleFromListScreen = MyListPageObject.getArticleTitleFromMyListScreen()
        //Открываем статью
        MyListPageObject.openFirstArticleFromMyListScreen()
        //Сохраняем название статьи с экрана самой статьи
        ArticlePageObject.waitForTitleElement()
        val titleFromArticleScreen = ArticlePageObject.getArticleTitle()
        //Сравнимаем названия статей
        assertEquals("Title not equals", titleFromListScreen, titleFromArticleScreen)
    }

    //Задание Ex6
    @Test
    fun testTitleIsPresent() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObject(driver)
        val NavigationUI: NavigationUI = NavigationUI(driver)
        val MyListPageObject:MyListPageObject = MyListPageObject(driver)

        //Тапаем на поле ввода
        SearchPageObject.initSearchInput()
        //Вводим текст
        SearchPageObject.typeSearchLine("Java")
        //Тапаем по первой статье из саджеста
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language")
        //Без задержки проверяем что элемент присутствует
        ArticlePageObject.checkTitlePresentWithoutTimeout()
    }

    @Test
    fun testSearch(){
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.waitForSearchResult("Object-oriented programming language")
    }

    @Test
    fun testSwipeUp() {
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Appium")
        SearchPageObject.clickByArticleWithSubstring("Appium")

        val ArticlePageObject: ArticlePageObject = ArticlePageObject(driver)
        ArticlePageObject.waitForTitleElement()
        ArticlePageObject.swipeToFooter()
    }

    @Test
    fun testAmountOfNotEmptySearch(){
        val SearchPageObject: SearchPageObject = SearchPageObject(driver)
        SearchPageObject.initSearchInput()
        val search_line = "Linkin Park Discography"
        SearchPageObject.typeSearchLine(search_line)
        val amount_of_search_results = SearchPageObject.getAmountOfFoundArticles()
        Assert.assertTrue("We found too few results", amount_of_search_results > 0)
    }
}
