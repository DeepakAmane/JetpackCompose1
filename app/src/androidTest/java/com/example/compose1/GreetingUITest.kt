package com.example.compose1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.compose1.view.MainActivity
import org.junit.Rule
import org.junit.Test


class GreetingUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun greetingText_isDisplayed() {
        // Assert that the text "Hello Android!" is displayed
        composeTestRule
            .onNodeWithText("Hello Android!")
            .assertIsDisplayed()
    }
}