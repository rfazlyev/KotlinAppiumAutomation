import lib.CoreTestCase
import lib.Platform
import lib.ui.ArticlePageObject
import lib.ui.MyListPageObject
import lib.ui.NavigationUI
import lib.ui.SearchPageObject
import lib.ui.factories.ArticlePageObjectFactory
import lib.ui.factories.MyListPageObjectFactory
import lib.ui.factories.NavigationUIPageObjectFactory
import lib.ui.factories.SearchPageObjectFactory
import org.junit.Test

class ArticleTests : CoreTestCase() {

    companion object {
        val name_of_folder = "Learning programming"
    }

    @Test
    fun testSaveTwoArticles() {
        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObjectFactory.get(driver)
        val NavigationUI: NavigationUI = NavigationUIPageObjectFactory.get(driver)
        val MyListPageObject: MyListPageObject = MyListPageObjectFactory.get(driver)

        //Тапаем на поле ввода
        SearchPageObject.initSearchInput()
        //Вводим текст
        SearchPageObject.typeSearchLine("Java")
        //Тапаем по первой статье из саджеста
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language")
        ArticlePageObject.waitForTitleElement()
        //Получаем название статьи
        val article_title = ArticlePageObject.getArticleTitle()

        if (Platform.getInstance().isAndroid()) {
            //Добавляем статью в новый список
            ArticlePageObject.addArticleToMyNewList(name_of_folder)
        } else {
            ArticlePageObject.addArticlesToMySaved()
        }
        //Закрываем статью
        ArticlePageObject.closeArticle()
        //Тапаем на поле ввода
        SearchPageObject.initSearchInput()
        // На iOS удаляем предыдущий ввод
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch()
        }
        //Вводим текст
        SearchPageObject.typeSearchLine("Java")
        //Тапаем по другой статье
        SearchPageObject.clickByArticleWithSubstring("High-level programming language")
        //Сохраняем ее в тот же список
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToExistList(name_of_folder)
        } else {
            ArticlePageObject.addArticlesToMySaved()
        }
        //Закрываем статью
        ArticlePageObject.closeArticle()
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCloseSearchScreen()
        }
        //Открываем экран списки для чтения
        NavigationUI.clickMyList()
        //Тапаем по нашему списку для Android
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder)
            // Закрываем модальное окно на iOS
        } else {
            MyListPageObject.clickButtonCloseSyncMyArticlesWindow()
        }

        //Удаляем одну статью
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.swipeByArticleToDelete(article_title)
        } else {
            MyListPageObject.swipeAndDeleteFirstArticleIos()
        }
        //Создаем переменную для сравнения заголовков
        val titleForAssert = "Java (programming language)"
        //Проверяем что статья с заданным заголовком отображается

        if(Platform.getInstance().isIOS()){
            MyListPageObject.articleIsPresent(titleForAssert)
        }

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.waitForTitleElementFromMyListScreen()
            //Сохраняем название статьи с экрана списка для чтения
            val titleFromListScreen = MyListPageObject.getArticleTitleFromMyListScreen()
            //Открываем статью
            MyListPageObject.openFirstArticleFromMyListScreen()
            //Сохраняем название статьи с экрана самой статьи
            ArticlePageObject.waitForTitleElement()
            val titleFromArticleScreen = ArticlePageObject.getArticleTitle()
            //Сравнимаем названия статей
            assertEquals("Title not equals", titleFromListScreen, titleFromArticleScreen)
        } else {
            //Открываем статью
            MyListPageObject.openFirstArticleIos()
            //Проверяем что заданный заголовок
            ArticlePageObject.titleIsPresent(titleForAssert)
        }
    }

    @Test
    fun testTitleIsPresent() {
        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        val ArticlePageObject: ArticlePageObject = ArticlePageObjectFactory.get(driver)
        val NavigationUI: NavigationUI = NavigationUIPageObjectFactory.get(driver)
        val MyListPageObject: MyListPageObject = MyListPageObjectFactory.get(driver)

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
    fun testSwipeUp() {
        val SearchPageObject: SearchPageObject = SearchPageObjectFactory.get(driver)
        SearchPageObject.initSearchInput()
        SearchPageObject.typeSearchLine("Java")
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language")

        val ArticlePageObject: ArticlePageObject = ArticlePageObjectFactory.get(driver)
        ArticlePageObject.waitForTitleElement()
        ArticlePageObject.swipeToFooter()
    }
}
