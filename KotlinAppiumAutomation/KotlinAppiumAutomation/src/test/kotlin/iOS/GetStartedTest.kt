package iOS

import lib.iOSTestCase
import lib.ui.WelcomePageObject
import org.junit.Test

class GetStartedTest: iOSTestCase() {

    @Test
    fun testPassThroughWelcome(){

        val WelcomePageObject = WelcomePageObject(driver)

        WelcomePageObject.waitForLearnMoreLink()
        WelcomePageObject.clickNextButton()

        WelcomePageObject.waitForNewWayToExploreText()
        WelcomePageObject.clickNextButton()

        WelcomePageObject.waitForAddOrEditPreferredLangText()
        WelcomePageObject.clickNextButton()

        WelcomePageObject.waitForLearnMoreAboutDataCollectedText()
        WelcomePageObject.clickGetStartedButton()
    }
}