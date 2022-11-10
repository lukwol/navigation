package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import io.github.lukwol.screens.navigation.data.ThirdScreenArgs
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.testutils.ComposeWebExperimentalTestsApi
import org.jetbrains.compose.web.testutils.runTest
import kotlin.test.Test

@ComposeWebExperimentalTestsApi
class ScreensNavigationTest {

    @Test
    fun navigatingToScreens() = runTest {
        lateinit var screensController: ScreensController
        composition {
            ScreensNavigation(
                startRoute = TestRoutes.FirstScreen
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

        root.innerHTML shouldBe "<div>First screen</div>"

        screensController.push(TestRoutes.SecondScreen, "Foo")
        waitForRecompositionComplete()

        root.innerHTML shouldBe """
            <div>Second screen</div>
            <div>args: Foo</div>
        """.trimIndent().replace("\n", "")

        screensController.push(TestRoutes.ThirdScreen, ThirdScreenArgs(text = "Bar", number = 42))
        waitForRecompositionComplete()

        root.innerHTML shouldBe """
            <div>Third screen</div>
            <div>args: text = Bar, number = 42</div>
        """.trimIndent().replace("\n", "")

        screensController.pop()
        waitForRecompositionComplete()

        root.innerHTML shouldBe """
            <div>Second screen</div>
            <div>args: Foo</div>
        """.trimIndent().replace("\n", "")

        screensController.pop()
        waitForRecompositionComplete()

        root.innerHTML shouldBe "<div>First screen</div>"
    }

    @Test
    fun missingStartRouteScreen() = runTest {
        shouldThrow<NoSuchElementException> {
            composition {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen
                ) {
                    screen(TestRoutes.SecondScreen) {}
                }
            }
        }
    }

    @Test
    fun emptyNavigationGraph() = runTest {
        shouldThrow<NoSuchElementException> {
            composition {
                ScreensNavigation(
                    startRoute = TestRoutes.FirstScreen
                ) {}
            }
        }
    }
}
