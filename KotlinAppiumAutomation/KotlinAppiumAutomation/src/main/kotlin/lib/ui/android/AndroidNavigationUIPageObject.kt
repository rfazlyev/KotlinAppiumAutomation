package lib.ui.android

import io.appium.java_client.AppiumDriver
import lib.ui.NavigationUI
import lib.ui.SearchPageObject
import org.openqa.selenium.WebElement

class AndroidNavigationUIPageObject(driver: AppiumDriver<WebElement>): NavigationUI(driver) {

    override val MY_LIST_LINK = "xpath://*[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']//*[@content-desc='My lists']"

}