package lib

import io.appium.java_client.AppiumDriver
import junit.framework.TestCase
import lib.ui.WelcomePageObject
import org.openqa.selenium.ScreenOrientation
import org.openqa.selenium.WebElement

open class CoreTestCase : TestCase() {

    lateinit var driver: AppiumDriver<WebElement>


    override fun setUp() {
        super.setUp()
        driver = Platform.getInstance().getDriver()
        this.rotateScreenPortrait()
        this.skipWelcomePageForIOSApp()
    }

    override fun tearDown() {
        driver.quit()
        super.tearDown()
    }

    protected fun rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT)
    }

    protected fun rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE)
    }

    protected fun backgroundApp(seconds: Int){
        driver.runAppInBackground(seconds)
    }

    private fun skipWelcomePageForIOSApp(){
        if(Platform.getInstance().isIOS()){
            val WelcomePageObject:WelcomePageObject = WelcomePageObject(driver)
            WelcomePageObject.clickSkip()
        }
    }
}
