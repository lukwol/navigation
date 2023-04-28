@file:OptIn(ExperimentalCoroutinesApi::class)

package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import io.github.lukwol.viewmodel.screens.navigation.components.TestScreenNavigation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ViewModelScreenNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun firstScreen(): Unit = with(compose) {
        setContent { TestScreenNavigation() }

        onNode(hasSetTextAction()).assert(hasText(""))
        onNode(hasSetTextAction()).performTextInput("Foo")
        onNode(hasSetTextAction()).assert(hasText("Foo"))
    }

    @Test
    fun navigateToSecondScreenAndUpdateItsState() = runTest {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNode(hasSetTextAction()).assert(hasText(""))
            onNode(hasSetTextAction()).performTextInput("Bar")
            onNode(hasSetTextAction()).assert(hasText("Bar"))
        }
    }

    @Test
    fun navigateBackAndForth() = runTest {
        with(compose) {
            setContent { TestScreenNavigation() }

            onNode(hasSetTextAction()).assert(hasText(""))
            onNode(hasSetTextAction()).performTextInput("Foo")
            onNode(hasSetTextAction()).assert(hasText("Foo"))

            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNode(hasSetTextAction()).assert(hasText("Foo"))
            onNode(hasSetTextAction()).performTextClearance()
            onNode(hasSetTextAction()).performTextInput("The quick brown fox")
            onNode(hasSetTextAction()).assert(hasText("The quick brown fox"))

            onNodeWithText("Pop Screen").performClick()
            awaitIdle()

            onNode(hasSetTextAction()).assert(hasText("Foo"))
            onNode(hasSetTextAction()).performTextInput("Bar")
            onNode(hasSetTextAction()).assert(hasText("FooBar"))

            onNodeWithText("Push Second Screen").performClick()
            awaitIdle()

            onNode(hasSetTextAction()).assert(hasText("FooBar"))
        }
    }
}
