package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

abstract class NavigationUI(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    abstract val MY_LIST_LINK: String

    fun clickMyList() {
        this.waitForElementAndClick(MY_LIST_LINK, "Button 'My Lists' not found", 10)
    }
}
