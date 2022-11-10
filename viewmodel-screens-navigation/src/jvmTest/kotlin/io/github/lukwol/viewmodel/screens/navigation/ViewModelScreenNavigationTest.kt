package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import io.github.lukwol.viewmodel.screens.navigation.components.TestScreenNavigation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelScreenNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Before
    fun setUp() {
        compose.setContent {
            TestScreenNavigation()
        }
    }

    @Test
    fun `first screen`(): Unit = with(compose) {
        onNode(hasSetTextAction()).assert(hasText(""))
        onNode(hasSetTextAction()).performTextInput("Foo")
        onNode(hasSetTextAction()).assert(hasText("Foo"))
    }

    @Test
    fun `navigate to second screen and update it's state`() {
        with(compose) {
            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNode(hasSetTextAction()).assert(hasText(""))
            onNode(hasSetTextAction()).performTextInput("Bar")
            onNode(hasSetTextAction()).assert(hasText("Bar"))
        }
    }

    @Test
    fun `navigate back and forth`() {
        with(compose) {
            onNode(hasSetTextAction()).assert(hasText(""))
            onNode(hasSetTextAction()).performTextInput("Foo")
            onNode(hasSetTextAction()).assert(hasText("Foo"))

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNode(hasSetTextAction()).assert(hasText("Foo"))
            onNode(hasSetTextAction()).performTextClearance()
            onNode(hasSetTextAction()).performTextInput("The quick brown fox")
            onNode(hasSetTextAction()).assert(hasText("The quick brown fox"))

            onNodeWithText("Pop Screen").performClick()
            waitForIdle()

            onNode(hasSetTextAction()).assert(hasText("Foo"))
            onNode(hasSetTextAction()).performTextInput("Bar")
            onNode(hasSetTextAction()).assert(hasText("FooBar"))

            onNodeWithText("Push Second Screen").performClick()
            waitForIdle()

            onNode(hasSetTextAction()).assert(hasText("FooBar"))
        }
    }
}
