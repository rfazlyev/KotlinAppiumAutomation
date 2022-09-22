package lib

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import junit.framework.TestCase
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

open class CoreTestCase : TestCase() {

    private val PLATFORM_IOS = "ios"
    private val PLATFORM_ANDROID = "android"

    lateinit var driver: AppiumDriver<WebElement>

    private val AppiumURL = "http://127.0.0.1:4723/wd/hub"

    override fun setUp() {
        super.setUp()
        val capabilities: DesiredCapabilities = getCapabilitiesByPlatformEnv()

        driver = AndroidDriver(URL(AppiumURL), capabilities)
    }

    override fun tearDown() {
        driver.quit()
        super.tearDown()
    }

    private fun getCapabilitiesByPlatformEnv(): DesiredCapabilities {

        val platform = System.getenv("PLATFORM")

        val capabilities = DesiredCapabilities()

        if (platform.equals(PLATFORM_ANDROID)) {
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
        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS")
            capabilities.setCapability("deviceName", "iPhone 13")
            capabilities.setCapability("platformVersion", "15.5")
            capabilities.setCapability(
                "app",
                "/Users/indriver/Desktop/KotlinAppiumAutomation/KotlinAppiumAutomation/apks/Wikipedia.app"
            )
        } else {
            throw Exception("Cannot get run platform from env variable. Platform value $platform")
        }
        return capabilities
    }
}
