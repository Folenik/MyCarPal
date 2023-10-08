package com.mosz.wikirandom

import android.util.Log
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.semantics.SemanticsProperties.Text
import com.mosz.wikirandom.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val defaultTimeout = 5000L

    @Test
    fun app_loads_with_progress_bar_and_disappears() {
        composeTestRule.onNodeWithTag(testTag = "pBar").assertIsDisplayed()
        composeTestRule.waitUntilDoesNotExist(
            matcher = hasTestTag("pBar"),
            timeoutMillis = defaultTimeout
        )
    }

    @Test
    fun basic_ui_components_are_present() {
        composeTestRule.onNodeWithTag(testTag = "pBar").assertIsDisplayed()
        composeTestRule.waitUntilDoesNotExist(
            matcher = hasTestTag("pBar"),
            timeoutMillis = defaultTimeout
        )
        composeTestRule.onNodeWithTag(testTag = "header").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "description").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(label = "image").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(label = "refresh").assertIsDisplayed()
    }

    @Test
    fun refresh_button_refreshes_random_article() {
        composeTestRule.onNodeWithTag(testTag = "pBar").assertIsDisplayed()
        composeTestRule.waitUntilDoesNotExist(
            matcher = hasTestTag("pBar"),
            timeoutMillis = defaultTimeout
        )
        composeTestRule.onNodeWithTag(testTag = "header").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "description").assertIsDisplayed()

        val initialHeaderText =
            composeTestRule.onNodeWithTag(testTag = "header").fetchSemanticsNode().config[Text][0].text
        val initialDescriptionText =
            composeTestRule.onNodeWithTag(testTag = "description").fetchSemanticsNode().config[Text][0].text

        composeTestRule.onNodeWithContentDescription(label = "refresh").assertIsDisplayed().performClick()

        //Waiting for the API call to finish
        runBlocking {
            delay(defaultTimeout)
        }

        val newHeaderText =
            composeTestRule.onNodeWithTag(testTag = "header").fetchSemanticsNode().config[Text][0].text
        val newDescriptionText =
            composeTestRule.onNodeWithTag(testTag = "description").fetchSemanticsNode().config[Text][0].text

        assertNotEquals(
            "Header text should have changed",
            initialHeaderText,
            newHeaderText
        )
        assertNotEquals(
            "Description text should have changed",
            initialDescriptionText,
            newDescriptionText
        )
    }
}