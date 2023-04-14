@file:OptIn(ExperimentalCoroutinesApi::class)

package io.github.lukwol.screens.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import io.github.lukwol.screens.navigation.components.TestScreenNavigation
import io.github.lukwol.screens.navigation.data.TestRoutes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFailsWith

class ScreensNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Before
    fun setUp() = compose.setContent {
        TestScreenNavigation()
    }

    @Test
    fun `first screen`(): Unit = with(compose) {
        onNodeWithText("Push Second Screen").assertExists()
    }

    @Test
    fun `navigate to second screen`() = runTest {
        with(compose) {
            onNodeWithText("Push Second Screen").assertExists()
            onNodeWithText("Foo").assertDoesNotExist()

            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNodeWithText("Push Second Screen").assertDoesNotExist()
            onNodeWithText("Foo").assertExists()
        }
    }

    @Test
    fun `navigate to third screen`() = runTest {
        with(compose) {
            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNodeWithText("Foo").assertExists()

            onNodeWithText("Push Third Screen").performClick()
            awaitIdle()

            onNodeWithText("Foo").assertDoesNotExist()
            onNodeWithText("text = Bar, number = 42").assertExists()
        }
    }

    @Test
    fun `navigate to third screen then pop to second screen`() = runTest {
        with(compose) {
            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNodeWithText("Push Third Screen").performClick()
            awaitIdle()

            onNodeWithText("text = Bar, number = 42").assertExists()
            onNodeWithText("Pop Screen").performClick()
            awaitIdle()

            onNodeWithText("text = Bar, number = 42").assertDoesNotExist()
            onNodeWithText("Foo").assertExists()
        }
    }

    @Test
    fun `navigate to third screen then pop to first screen`() = runTest {
        with(compose) {
            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNodeWithText("Push Third Screen").performClick()
            awaitIdle()

            onNodeWithText("text = Bar, number = 42").assertExists()
            onNodeWithText("Pop To First Screen").performClick()
            awaitIdle()

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
