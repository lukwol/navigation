package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import io.github.lukwol.screens.navigation.data.ThirdScreenArgs
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ComposeWebExperimentalTestsApi
class ScreensNavigationTest {

    @Test
    fun navigatingToScreens() = runTest {
        lateinit var screensController: ScreensController
        composition {
            ScreensNavigation(
                startRoute = TestRoutes.FirstScreen,
            ) {
                screen(TestRoutes.FirstScreen) {
                    screensController = LocalScreensController.current
                    Div {
                        Text("First screen")
                    }
                }

                screen(TestRoutes.SecondScreen) { args ->
                    Div {
                        Text("Second screen")
                    }
                    Div {
                        Text("args: ${args as String}")
                    }
                }

                screen(TestRoutes.ThirdScreen) { args ->
                    Div {
                        Text("Third screen")
                    }
                    Div {
                        Text("args: ${args as ThirdScreenArgs}")
                    }
                }
            }
        }

        assertEquals("<div>First screen</div>", root.innerHTML)

        screensController.push(TestRoutes.SecondScreen, "Foo")
        waitForRecompositionComplete()

        assertEquals(
            """
            <div>Second screen</div>
            <div>args: Foo</div>
            """.trimIndent().replace("\n", ""),
            root.innerHTML,
        )

        screensController.push(TestRoutes.ThirdScreen, ThirdScreenArgs(text = "Bar", number = 42))
        waitForRecompositionComplete()

        assertEquals(
            """
            <div>Third screen</div>
            <div>args: text = Bar, number = 42</div>
            """.trimIndent().replace("\n", ""),
            root.innerHTML,
        )

        screensController.pop()
        waitForRecompositionComplete()

        assertEquals(
            """
            <div>Second screen</div>
            <div>args: Foo</div>
            """.trimIndent().replace("\n", ""),
            root.innerHTML,
        )

        screensController.pop()
        waitForRecompositionComplete()

        assertEquals("<div>First screen</div>", root.innerHTML)
    }

    @Test
    fun missingStartRouteScreen() = runTest {
        assertFailsWith<NoSuchElementException> {
            composition {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen,
                ) {
                    screen(TestRoutes.SecondScreen) {}
                }
            }
        }
    }

    @Test
    fun emptyNavigationGraph() = runTest {
        assertFailsWith<NoSuchElementException> {
            composition {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen,
                ) {}
            }
        }
    }
}
