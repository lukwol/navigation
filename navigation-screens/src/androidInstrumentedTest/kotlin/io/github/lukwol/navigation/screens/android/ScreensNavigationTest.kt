package io.github.lukwol.navigation.screens.android

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
