package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class WelcomePageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    private val STEP_LEARN_MORE_LINK = "//XCUIElementTypeButton[@name=\"Learn more about Wikipedia\"]"
    private val STEP_NEW_WAYS_TO_EXPLORER_TEXT = "New ways to explore"
    private val STEP_ADD_OR_EDIT_PREFERED_LANG_LINK = "//XCUIElementTypeButton[@name=\"Add or edit preferred languages\"]"
    private val STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "//XCUIElementTypeStaticText[@name=\"Learn more about data collected\"]"
    private val NEXT_LINK = "//XCUIElementTypeButton[@name=\"Next\"]"
    private val GET_STARTED_BUTTON = "//XCUIElementTypeButton[@name=\"Get started\"]"

     fun waitForLearnMoreLink() {
        this.waitForElementPresent(
            By.xpath(STEP_LEARN_MORE_LINK),
            "Cannot find 'Learn more about Wikipedia' link",
            10
        )
    }

    fun waitForNewWayToExploreText() {
        this.waitForElementPresent(
            By.id(STEP_NEW_WAYS_TO_EXPLORER_TEXT),
            "Cannot find 'New ways to explore' text",
            10
        )
    }

    fun waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(
            By.xpath(STEP_ADD_OR_EDIT_PREFERED_LANG_LINK),
            "Cannot find 'Add or edit preferred languages' text",
            10
        )
    }

    fun waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(
            By.xpath(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK),
            "Cannot find 'Learn more about data collected' text",
            10
        )
    }

    fun clickNextButton() {
        this.waitForElementAndClick(
            By.xpath(NEXT_LINK),
            "Cannot find and click 'Next' link",
            10
        )
    }

    fun clickGetStartedButton() {
        this.waitForElementAndClick(
            By.xpath(GET_STARTED_BUTTON),
            "Cannot find and click 'Get started' link",
            10
        )
    }
 }
