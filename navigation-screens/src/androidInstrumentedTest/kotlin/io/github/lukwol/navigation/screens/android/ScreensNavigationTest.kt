package io.github.lukwol.navigation.screens.android

import androidx.compose.ui.test.junit4.createComposeRule
import io.github.lukwol.navigation.screens.ScreensNavigation
import io.github.lukwol.navigation.screens.data.TestRoutes
import kotlin.test.assertFailsWith
import org.junit.Rule
import org.junit.Test

class ScreensNavigationTest {

    @get:Rule
    val compose = createComposeRule()

    @Test
    fun missingStartRouteScreen() {
        assertFailsWith<IllegalArgumentException> {
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
    fun emptyNavigationGraph() {
        assertFailsWith<IllegalArgumentException> {
            compose.setContent {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen,
                ) {}
            }
        }
    }
}
