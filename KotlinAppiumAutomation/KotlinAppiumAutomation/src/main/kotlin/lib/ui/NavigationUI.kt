package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class NavigationUI(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    private val MY_LIST_LINK =
        "//*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"

    fun clickMyList() {
        this.waitForElementAndClick(By.xpath(MY_LIST_LINK), "Button 'My Lists' not found", 10)
    }
}