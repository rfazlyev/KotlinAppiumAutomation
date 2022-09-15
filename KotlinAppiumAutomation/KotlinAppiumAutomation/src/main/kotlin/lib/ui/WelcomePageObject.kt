package lib.ui

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

class WelcomePageObject(driver: AppiumDriver<WebElement>) : MainPageObject(driver) {

    private val STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeButton[@name=\"Узнать подробнее о Википедии\"]"
    private val STEP_NEW_WAYS_TO_EXPLORER_TEXT = "xpath://XCUIElementTypeStaticText[@name=\"Новые способы изучения\"]"
    private val STEP_ADD_OR_EDIT_PREFERED_LANG_LINK =
        "xpath://XCUIElementTypeButton[@name=\"Добавить или изменить предпочтительные языки\"]"
    private val STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK =
        "xpath://XCUIElementTypeStaticText[@name=\"Узнать подробнее о сборе данных\"]"
    private val NEXT_LINK = "xpath://XCUIElementTypeButton[@name=\"Далее\"]"
    private val GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Начать\"]"

    fun waitForLearnMoreLink() {
        this.waitForElementPresent(
            STEP_LEARN_MORE_LINK,
            "Cannot find 'Learn more about Wikipedia' link",
            10
        )
    }

    fun waitForNewWayToExploreText() {
        this.waitForElementPresent(
            STEP_NEW_WAYS_TO_EXPLORER_TEXT,
            "Cannot find 'New ways to explore' text",
            10
        )
    }

    fun waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(
            STEP_ADD_OR_EDIT_PREFERED_LANG_LINK,
            "Cannot find 'Add or edit preferred languages' text",
            10
        )
    }

    fun waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
            "Cannot find 'Learn more about data collected' text",
            10
        )
    }

    fun clickNextButton() {
        this.waitForElementAndClick(
            NEXT_LINK,
            "Cannot find and click 'Next' link",
            10
        )
    }

    fun clickGetStartedButton() {
        this.waitForElementAndClick(
            GET_STARTED_BUTTON,
            "Cannot find and click 'Get started' link",
            10
        )
    }
}
