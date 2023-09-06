package io.github.lukwol.navigation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.github.lukwol.navigation.screens.components.TestScreenNavigation
import org.junit.Rule
import org.junit.Test

class ScreensNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun firstScreen(): Unit = with(compose) {
        setContent { TestScreenNavigation() }

        onNodeWithText("Push Second Screen").assertExists()
    }

    @Test
    fun navigateToSecondScreen() {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNodeWithText("Push Second Screen").assertExists()
            onNodeWithText("Foo").assertDoesNotExist()

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNodeWithText("Push Second Screen").assertDoesNotExist()
            onNodeWithText("Foo").assertExists()
        }
    }

    @Test
    fun navigateToThirdScreen() {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNodeWithText("Foo").assertExists()

            onNodeWithText("Push Third Screen").performClick()
            waitForIdle()

            onNodeWithText("Foo").assertDoesNotExist()
            onNodeWithText("text = Bar, number = 42").assertExists()
        }
    }

    @Test
    fun navigateToThirdScreenThenPopToSecondScreen() {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNodeWithText("Push Third Screen").performClick()
            waitForIdle()

            onNodeWithText("text = Bar, number = 42").assertExists()
            onNodeWithText("Pop Screen").performClick()
            waitForIdle()

            onNodeWithText("text = Bar, number = 42").assertDoesNotExist()
            onNodeWithText("Foo").assertExists()
        }
    }

    @Test
    fun navigateToThirdScreenThenPopToFirstScreen() {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNodeWithText("Push Third Screen").performClick()
            waitForIdle()

            onNodeWithText("text = Bar, number = 42").assertExists()
            onNodeWithText("Pop To First Screen").performClick()
            waitForIdle()

            onNodeWithText("text = Bar, number = 42").assertDoesNotExist()
            onNodeWithText("Push Second Screen").assertExists()
        }
    }
}