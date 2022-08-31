import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
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
    fun checkTextInInputField() {

        assertElementHasText(
            By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
            "Search Wikipedia",
            "Expected text not found"
        )
    }

    @Test
    fun cancelSearch(){

        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),"Element not found",10)

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"),"Hello","Element not found",10)

        waitForElementPresent(By.id("org.wikipedia:id/search_results_container"),"Element not found",10)

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Element not found",10)

        waitForElementNotPresent(By.id("org.wikipedia:id/search_results_container"), "Element found",10)

    }

    fun assertElementHasText(by: By, title: String, errorMessage: String) {

        val element: WebElement = waitForElementPresent(by, errorMessage, 10)

        val textFromElement = element.text

        Assert.assertEquals(errorMessage, title, textFromElement)
    }

    fun waitForElementPresent(by: By, errorMessage: String, timeoutInSeconds: Long): WebElement {

        val wait = WebDriverWait(driver, timeoutInSeconds)

        wait.withMessage(errorMessage + "\n")

        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
    }
}
