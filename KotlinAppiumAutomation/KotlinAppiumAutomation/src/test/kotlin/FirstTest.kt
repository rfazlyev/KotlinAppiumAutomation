import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.ScreenOrientation
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL

class FirstTest {

    private lateinit var driver: AppiumDriver<WebElement>

    @Before
    fun setUp() {

        val capabilities = DesiredCapabilities()

        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("deviceName", "AndroidTestDevice")
        capabilities.setCapability("platformVersion", "8.0")
        capabilities.setCapability("automationName", "Appium")
        capabilities.setCapability("appPackage", "org.wikipedia")
        capabilities.setCapability("appActivity", ".main.MainActivity")
        capabilities.setCapability(
            "app",
            "/Users/indriver/Desktop/KotlinAppiumAutomation/KotlinAppiumAutomation/apks/org.wikipedia.apk"
        )

        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
    }

    @After
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun titleIsPresent(){
        val textForSearch = "Java"
        val buttonSearchPage = "org.wikipedia:id/search_container"
        val inputFieldForSearch = "org.wikipedia:id/search_src_text"
        val firstArticleFromResult =
            "//*[@resource-id='org.wikipedia:id/search_results_list']/android.widget.LinearLayout[1]"
        val titleArticle = "org.wikipedia:id/view_page_title_text"

        //Тапаем на кнопку поиска
        waitForElementAndClick(By.id(buttonSearchPage),"Element search_container not found",10)
        //Вводим запрос
        waitForElementAndSendKeys(
            By.id(inputFieldForSearch),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по первой статье
        waitForElementAndClick(By.xpath(firstArticleFromResult), "First article not found in result list",10)
        //Без задержки проверяем что элемент присутствует
        assertElementPresent(By.id(titleArticle), "Title not present")
    }

    @Test
    fun saveTwoArticles() {
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
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element search_container not found", 10)
        //Вводим текст из переменной $textForSearch
        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по первой статье из саджеста
        waitForElementAndClick(
            By.xpath(firstArticleFromResult),
            "First article not found in result list",
            10
        )
        //Тапаем на кебаб-меню
        waitForElementAndClick(By.xpath(buttonKebabMenu), "Button kebab-menu not found", 10)
        //Тапаем сохранить в список для чтения
        waitForElementAndClick(By.xpath(buttonAddToReadList), "Button add to read list not found", 10)
        //Проходим онбординг
        waitForElementAndClick(By.id(buttonOnboarding), "Button 'GOT IT' not found", 10)
        //Стираем дефолтное название списка
        waitForElementAndClear(By.id(inputFieldReadList), "Input field not found", 10)
        //Вводим название списка из переменной readListName
        waitForElementAndSendKeys(
            By.id(inputFieldReadList),
            readListName,
            "Error send keys $readListName",
            10
        )
        //Тапаем ОК на алерте
        waitForElementAndClick(
            By.xpath(buttonOkFromAlert),
            "Second article not found in result list",
            10
        )
        //Нажимаем на кнопку поиска
        waitForElementAndClick(By.id(buttonPageSearch), "Button page search not found", 10)
        //Снова вводим $textForSearch
        waitForElementAndSendKeys(
            By.id("org.wikipedia:id/search_src_text"),
            textForSearch,
            "Error send keys $textForSearch",
            10
        )
        //Тапаем по второй статье из саджеста
        waitForElementAndClick(
            By.xpath(secondArticleFromResult),
            "First article not found in result list",
            10
        )
        //Тапаем на кебаб-меню
        waitForElementAndClick(By.xpath(buttonKebabMenu), "Button kebab-menu not found", 10)
        //Тапаем сохранить в список для чтения
        waitForElementAndClick(By.xpath(buttonAddToReadList), "Button add to read list not found", 10)
        //Выбираем в какой список сохранить статью
        waitForElementAndClick(By.xpath(buttonReadListNameFromBottomSheet), "Reading list not found", 10)
        //Закрываем статью
        waitForElementAndClick(By.xpath(buttonCloseArticle), "Button close not found", 10)
        //Открываем экран списки для чтения
        waitForElementAndClick(By.xpath(buttonMyListFromNavBar), "Button 'My Lists' not found", 10)
        //Тапаем по нашему списку
        waitForElementAndClick(By.xpath(buttonMyListFromMyListScreen), "List not $readListName found", 10)
        //Удаляем вторую статью
        swipeElementToLeft(By.xpath(secondArticleFromMyList), "Second article wasn't deleted")
        //Проверяем что первая осталась
        waitForElementPresent(By.xpath(firstArticleFromMyList), "First article not present", 10)
        //Сохраняем название статьи с экрана списка для чтения
        val firstArticleTitleFromListScreen = waitForElementAndGetAttributeText(
            By.xpath(firstArticleFromMyList),
            "Title from article not found in reading list screen",
            10
        )
        //Открываем статью
        waitForElementAndClick(By.xpath(firstArticleFromMyList), "First article not found", 10)
        //Сохраняем название статьи с экрана самой статьи
        val firstArticleTitleFromArticleScreen = waitForElementAndGetAttributeText(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Title from article not found in article screen",
            10
        )
        //Сравнимаем названия статей
        Assert.assertEquals("Title not equals", firstArticleTitleFromListScreen, firstArticleTitleFromArticleScreen)
    }

    @Test
    fun checkTextInInputField() {
        assertElementHasText(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
            "Search Wikipedia",
            "Expected text not found"
        )
    }

    @Test
    fun cancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element not found", 10)

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Hello", "Element not found", 10)

        waitForElementPresent(By.id("org.wikipedia:id/search_results_container"), "Element not found", 10)

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Element not found", 10)

        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_container"), "Element found", 10)
    }

    @Test
    fun swipeUp() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Element not found", 10)

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Appium", "Element not found", 10)

        waitForElementAndClick(
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
            "Element not found",
            10
        )

        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"), "Not found", 20)
    }

    private fun assertElementPresent(by: By, errorMessage: String) : WebElement{
        val element = WebDriverWait(driver,0)
        element.withMessage(errorMessage+ "\n")
        return element.until(ExpectedConditions.presenceOfElementLocated(by))

    }
    private fun waitForElementPresent(by: By, errorMessage: String, timeoutInSeconds: Long): WebElement {
        val wait = WebDriverWait(driver, timeoutInSeconds)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
    }

    private fun waitForElementNotPresent(by: By, errorMessage: String, timeout: Long): Boolean {
        val wait = WebDriverWait(driver, timeout)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

    private fun waitForElementAndClick(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.click()
        return element
    }

    private fun waitForElementAndSendKeys(by: By, text: String, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.sendKeys(text)
        return element
    }

    private fun waitForElementAndClear(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.clear()
        return element
    }

    fun assertElementHasText(by: By, title: String, errorMessage: String) {
        val element: WebElement = waitForElementPresent(by, errorMessage, 10)
        val textFromElement = element.text
        Assert.assertEquals(errorMessage, title, textFromElement)
    }

    private fun swipeUp(timeOfSwipe: Int) {
        val action: TouchAction = TouchAction(driver)
        val size: Dimension = driver.manage().window().size
        val x = size.width / 2
        val start_y: Int = (size.height * 0.8).toInt()
        val end_y: Int = (size.height * 0.2).toInt()

        action
            .press(x, start_y)
            .waitAction(timeOfSwipe)
            .moveTo(x, end_y)
            .release()
            .perform()
    }

    private fun swipeQuick() {
        swipeUp(200)
    }

    private fun swipeUpToFindElement(by: By, errorMessage: String, maxSwipes: Int) {
        var alreadySwiped = 0
        while (driver.findElements(by).size == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0)
                return
            }
            swipeQuick()
            alreadySwiped++
        }
    }

    private fun swipeElementToLeft(by: By, errorMessage: String) {
        val element: WebElement = waitForElementPresent(by, errorMessage, 10)

        val left_x: Int = element.location.getX()
        val right_x: Int = left_x + element.size.width
        val upper_y: Int = element.location.getY()
        val lower_y: Int = upper_y + element.size.height
        val middle_y: Int = (upper_y + lower_y) / 2

        val action: TouchAction = TouchAction(driver)
        action
            .press(right_x, middle_y)
            .waitAction(350)
            .moveTo(left_x, middle_y)
            .release()
            .perform()
    }

    private fun getAmountOfElements(by: By): Int {
        val elements = listOf(driver.findElements(by))
        return elements.size
    }

    private fun assertElementNotPresent(by: By, errorMessage: String) {
        val amountOfElements = getAmountOfElements(by)
        if (amountOfElements > 0) {
            val defaultMessage = "An element '$by' supposed to be not present"
            throw AssertionError("$defaultMessage $errorMessage")
        }
    }

    private fun waitForElementAndGetAttributeText(
        by: By,
        errorMessage: String,
        timeoutInSeconds: Long
    ): String {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeoutInSeconds)
        return element.text
    }

    private fun rotateDevice(type: String) {
        when (type) {
            "landscape" -> driver.rotate(ScreenOrientation.LANDSCAPE)
            "portrait" -> driver.rotate(ScreenOrientation.PORTRAIT)
        }
    }

    private fun appInBackground(timeInSeconds: Int) {
        driver.runAppInBackground(timeInSeconds)
    }
}
