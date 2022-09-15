package lib.ui

import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.ScreenOrientation
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.regex.Pattern

open class MainPageObject(
    private val driver: AppiumDriver<WebElement>
) {
    fun assertElementPresentWithoutTimeout(by: By, errorMessage: String): WebElement {
        val element = WebDriverWait(driver, 0)
        element.withMessage(errorMessage + "\n")
        return element.until(ExpectedConditions.presenceOfElementLocated(by))
    }

    fun waitForElementPresent(/*locator: String*/by: By, errorMessage: String, timeoutInSeconds: Long): WebElement {
        /*val by = this.getLocatorByString(locator)*/
        val wait = WebDriverWait(driver, timeoutInSeconds)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.presenceOfElementLocated(by))
    }

    fun waitForElementNotPresent(by: By, errorMessage: String, timeout: Long): Boolean {
        val wait = WebDriverWait(driver, timeout)
        wait.withMessage(errorMessage + "\n")
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by))
    }

    fun waitForElementAndClick(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.click()
        return element
    }

    fun waitForElementAndSendKeys(by: By, text: String, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.sendKeys(text)
        return element
    }

    fun waitForElementAndClear(by: By, errorMessage: String, timeout: Long): WebElement {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeout)
        element.clear()
        return element
    }

    fun assertElementHasText(by: By, title: String, errorMessage: String) {
        val element: WebElement = waitForElementPresent(by, errorMessage, 10)
        val textFromElement = element.text
        Assert.assertEquals(errorMessage, title, textFromElement)
    }

    fun swipeUp(timeOfSwipe: Int) {
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

    fun swipeQuick() {
        swipeUp(200)
    }

    fun swipeUpToFindElement(by: By, errorMessage: String, maxSwipes: Int) {
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

    fun swipeElementToLeft(by: By, errorMessage: String) {
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

    fun getAmountOfElements(by: By): Int {
        val elements = listOf(driver.findElements(by))
        return elements.size
    }

    fun assertElementNotPresent(by: By, errorMessage: String) {
        val amountOfElements = getAmountOfElements(by)
        if (amountOfElements > 0) {
            val defaultMessage = "An element '$by' supposed to be not present"
            throw AssertionError("$defaultMessage $errorMessage")
        }
    }

    fun waitForElementAndGetAttributeText(
        by: By,
        errorMessage: String,
        timeoutInSeconds: Long
    ): String {
        val element: WebElement = waitForElementPresent(by, errorMessage, timeoutInSeconds)
        return element.text
    }

    fun rotateDevice(type: String) {
        when (type) {
            "landscape" -> driver.rotate(ScreenOrientation.LANDSCAPE)
            "portrait" -> driver.rotate(ScreenOrientation.PORTRAIT)
        }
    }

    fun appInBackground(timeInSeconds: Int) {
        driver.runAppInBackground(timeInSeconds)
    }

    fun getLocatorByString(locator_with_type: String):By{
        val exploded_locator = locator_with_type.split(Pattern.quote(":"), limit = 2)
        val by_type = exploded_locator[0]
        val locator = exploded_locator[0]

        if(by_type.equals("xpath")){
            return By.xpath(locator)
        }
        else if(by_type.equals("id")){
            return By.id(locator)
        }
        else{
            throw java.lang.IllegalArgumentException("Cannot get type of locator. Locator $locator_with_type")
        }
    }

}
