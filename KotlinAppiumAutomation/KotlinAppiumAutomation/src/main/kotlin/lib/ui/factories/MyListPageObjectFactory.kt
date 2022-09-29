package lib.ui.factories

import io.appium.java_client.AppiumDriver
import lib.Platform
import lib.ui.MyListPageObject
import lib.ui.android.AndroidMyListPageObject
import lib.ui.ios.IOSMyListPageObject
import org.openqa.selenium.WebElement

class MyListPageObjectFactory {
    companion object {
        fun get(driver: AppiumDriver<WebElement>): MyListPageObject {
            if (Platform.getInstance().isAndroid()) return AndroidMyListPageObject(driver)
            else return IOSMyListPageObject(driver)
        }
    }
}