package com.stitchfix.navigationrecomposingbug

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stitchfix.navigationrecomposingbug.ui.theme.NavigationRecomposingBugTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ResumedStateTest {

    @get:Rule val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun maxLifecycleIsResumed() {
        composeTestRule.onNodeWithText("Home").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Screen: home").assertIsDisplayed()
        composeTestRule.onNodeWithText("State: RESUMED").assertIsDisplayed()
    }
}
