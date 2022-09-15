package lib

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class  iOSTestCase : TestCase() {

    lateinit var driver: AppiumDriver<WebElement>

    val AppiumURL = "http://127.0.0.1:4723/wd/hub"

    override fun setUp() {
        super.setUp()
        val capabilities = DesiredCapabilities()

        capabilities.setCapability("platformName", "iOS")
        capabilities.setCapability("deviceName", "iPhone 13")
        capabilities.setCapability("platformVersion", "15.5")
        capabilities.setCapability(
            "app",
            "/Users/indriver/Desktop/KotlinAppiumAutomation/KotlinAppiumAutomation/apks/Wikipedia.app"
        )

        driver = IOSDriver(URL(AppiumURL), capabilities)
    }

    override fun tearDown() {
        driver.quit()
        super.tearDown()
    }
}