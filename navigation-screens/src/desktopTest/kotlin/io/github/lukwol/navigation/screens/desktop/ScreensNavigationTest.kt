package io.github.lukwol.navigation.screens.desktop

import androidx.compose.ui.test.junit4.createComposeRule
import io.github.lukwol.navigation.screens.ScreensNavigation
import io.github.lukwol.navigation.screens.data.TestRoutes
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFailsWith

class ScreensNavigationTest {

    @get:Rule
    val compose = createComposeRule()

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
