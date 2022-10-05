package lib.ui.factories

import io.appium.java_client.AppiumDriver
import lib.Platform
import lib.ui.NavigationUI
import lib.ui.android.AndroidNavigationUIPageObject
import lib.ui.ios.IOSNavigationUIPageObject
import org.openqa.selenium.WebElement

class NavigationUIPageObjectFactory {
    companion object {
        fun get(driver: AppiumDriver<WebElement>): NavigationUI {
            if (Platform.getInstance().isAndroid()) return AndroidNavigationUIPageObject(driver)
            else return IOSNavigationUIPageObject(driver)
        }
    }
}
