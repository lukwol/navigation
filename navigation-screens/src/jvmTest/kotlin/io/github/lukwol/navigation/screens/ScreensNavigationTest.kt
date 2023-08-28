package io.github.lukwol.navigation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.github.lukwol.navigation.screens.components.TestScreenNavigation
import io.github.lukwol.navigation.screens.data.TestRoutes
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFailsWith

class ScreensNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun `first screen`(): Unit = with(compose) {
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
    fun `navigate to third screen`() {
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
    fun `navigate to third screen then pop to second screen`() {
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
    fun `navigate to third screen then pop to first screen`() {
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

    @Test
    fun `missing start route screen`() {
        assertFailsWith<NoSuchElementException> {
            compose.setContent {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen,
                ) {
                    screen(TestRoutes.SecondScreen) {}
                }
            }
        }
    }

    @Test
    fun `empty navigation graph`() {
        assertFailsWith<NoSuchElementException> {
            compose.setContent {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen,
                ) {}
            }
        }
    }
}
