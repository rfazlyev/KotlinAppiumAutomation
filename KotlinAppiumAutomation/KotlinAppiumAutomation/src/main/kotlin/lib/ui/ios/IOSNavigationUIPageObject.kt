package lib.ui.ios

import io.appium.java_client.AppiumDriver
import lib.ui.NavigationUI
import org.openqa.selenium.WebElement

class IOSNavigationUIPageObject(driver: AppiumDriver<WebElement>): NavigationUI(driver) {
    override val MY_LIST_LINK = "xpath://XCUIElementTypeButton[@name='Saved']"
}