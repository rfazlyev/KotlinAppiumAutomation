import lib.CoreTestCase
import lib.ui.MainPageObject
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class FirstTest : CoreTestCase() {

     lateinit var MainPageObject: MainPageObject

     override fun setUp() {
        super.setUp()
        MainPageObject = MainPageObject(driver)
    }

    @Test
    fun testTitleIsPresent() {
        val textForSearch = "Java"
        val buttonSearchPage = "org.wikipedia:id/search_container"
        val inputFieldForSearch = "org.wikipedia:id/search_src_text"
        val firstArticleFromResult =
            "//*[@resource-id='org.wikipedia:id/search_results_list']/android.widget.LinearLayout[1]"
        val titleArticle = "org.wikipedia:id/view_page_title_text"

        //Тапаем на кнопку поиска
        MainPageObject.waitForElementAndClick(By.id(buttonSearchPage), "Element search_container not found", 10)
        //Вводим запрос
        MainPageObject.waitForElementAndSendKeys(
            By.id(inputFieldForSearch),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по первой статье
        MainPageObject.waitForElementAndClick(By.xpath(firstArticleFromResult), "First article not found in result list", 10)
        //Без задержки проверяем что элемент присутствует
        MainPageObject.assertElementPresent(By.id(titleArticle), "Title not present")
    }


    @Test
    fun testSaveTwoArticles() {
        // Возможно много переменных, но при изменении теста, как было сказано в видео-уроке проще исправить их, чем искать по каждому шагу локатор
        val textForSearch = "Java"
        val firstArticleFromResult =
            "//*[@resource-id='org.wikipedia:id/search_results_list']/android.widget.LinearLayout[1]"
        val secondArticleFromResult =
            "//*[@resource-id='org.wikipedia:id/search_results_list']/android.widget.LinearLayout[2]"
        val buttonKebabMenu = "//android.widget.ImageView[@content-desc=\"More options\"]"
        val buttonAddToReadList = "//*[@text='Add to reading list']"
        val buttonOnboarding = "org.wikipedia:id/onboarding_button"
        val inputFieldReadList = "org.wikipedia:id/text_input"
        val readListName = "Education"
        val buttonOkFromAlert = "//*[@resource-id='org.wikipedia:id/buttonPanel']//*[@text='OK']"
        val buttonPageSearch = "org.wikipedia:id/menu_page_search"
        val buttonCloseArticle = "//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@content-desc='Navigate up']"
        val buttonReadListNameFromBottomSheet = "//*[@text='$readListName']"
        val buttonMyListFromNavBar =
            "//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"
        val buttonMyListFromMyListScreen = "//*[@text='$readListName']"
        val firstArticleFromMyList =
            "//*[@resource-id='org.wikipedia:id/reading_list_contents']/android.widget.FrameLayout[2]//android.widget.TextView[1]"
        val secondArticleFromMyList =
            "//*[@resource-id='org.wikipedia:id/reading_list_contents']/android.widget.FrameLayout[3]//android.widget.TextView[1]"

        //Сам тест

        //Тапаем на поле ввода
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element search_container not found", 10)
        //Вводим текст из переменной $textForSearch
        MainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по первой статье из саджеста
        MainPageObject.waitForElementAndClick(
            By.xpath(firstArticleFromResult),
            "First article not found in result list",
            10
        )
        //Тапаем на кебаб-меню
        MainPageObject.waitForElementAndClick(By.xpath(buttonKebabMenu), "Button kebab-menu not found", 10)
        //Тапаем сохранить в список для чтения
        MainPageObject.waitForElementAndClick(By.xpath(buttonAddToReadList), "Button add to read list not found", 10)
        //Проходим онбординг
        MainPageObject.waitForElementAndClick(By.id(buttonOnboarding), "Button 'GOT IT' not found", 10)
        //Стираем дефолтное название списка
        MainPageObject.waitForElementAndClear(By.id(inputFieldReadList), "Input field not found", 10)
        //Вводим название списка из переменной readListName
        MainPageObject.waitForElementAndSendKeys(
            By.id(inputFieldReadList),
            readListName,
            "Error send keys $readListName",
            10
        )
        //Тапаем ОК на алерте
        MainPageObject.waitForElementAndClick(
            By.xpath(buttonOkFromAlert),
            "Second article not found in result list",
            10
        )
        //Нажимаем на кнопку поиска
        MainPageObject.waitForElementAndClick(By.id(buttonPageSearch), "Button page search not found", 10)
        //Снова вводим $textForSearch
        MainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по второй статье из саджеста
        MainPageObject.waitForElementAndClick(
            By.xpath(secondArticleFromResult),
            "First article not found in result list",
            10
        )
        //Тапаем на кебаб-меню
        MainPageObject.waitForElementAndClick(By.xpath(buttonKebabMenu), "Button kebab-menu not found", 10)
        //Тапаем сохранить в список для чтения
        MainPageObject.waitForElementAndClick(By.xpath(buttonAddToReadList), "Button add to read list not found", 10)
        //Выбираем в какой список сохранить статью
        MainPageObject.waitForElementAndClick(By.xpath(buttonReadListNameFromBottomSheet), "Reading list not found", 10)
        //Закрываем статью
        MainPageObject.waitForElementAndClick(By.xpath(buttonCloseArticle), "Button close not found", 10)
        //Открываем экран списки для чтения
        MainPageObject.waitForElementAndClick(By.xpath(buttonMyListFromNavBar), "Button 'My Lists' not found", 10)
        //Тапаем по нашему списку
        MainPageObject.waitForElementAndClick(By.xpath(buttonMyListFromMyListScreen), "List not $readListName found", 10)
        //Удаляем вторую статью
        MainPageObject.swipeElementToLeft(By.xpath(secondArticleFromMyList), "Second article wasn't deleted")
        //Проверяем что первая осталась
        MainPageObject.waitForElementPresent(By.xpath(firstArticleFromMyList), "First article not present", 10)
        //Сохраняем название статьи с экрана списка для чтения
        val firstArticleTitleFromListScreen = MainPageObject.waitForElementAndGetAttributeText(
            By.xpath(firstArticleFromMyList),
            "Title from article not found in reading list screen",
            10
        )
        //Открываем статью
        MainPageObject.waitForElementAndClick(By.xpath(firstArticleFromMyList), "First article not found", 10)
        //Сохраняем название статьи с экрана самой статьи
        val firstArticleTitleFromArticleScreen = MainPageObject.waitForElementAndGetAttributeText(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Title from article not found in article screen",
            10
        )
        //Сравнимаем названия статей
        Assert.assertEquals("Title not equals", firstArticleTitleFromListScreen, firstArticleTitleFromArticleScreen)
    }

    @Test
    fun testCheckTextInInputField() {
        MainPageObject.assertElementHasText(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
            "Search Wikipedia",
            "Expected text not found"
        )
    }

    @Test
    fun testCancelSearch() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element not found", 10)

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Hello", "Element not found", 10)

        MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_results_container"), "Element not found", 10)

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Element not found", 10)

        MainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_results_container"), "Element found", 10)
    }

    @Test
    fun testSwipeUp() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element not found", 10)

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Element not found", 10)

        MainPageObject.waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
            "Element not found",
            10
        )

        MainPageObject.swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"), "Not found", 20)
    }
}
