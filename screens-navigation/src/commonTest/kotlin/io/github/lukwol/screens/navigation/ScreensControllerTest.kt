package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScreensControllerTest {

    private lateinit var screensController: ScreensController

    @BeforeTest
    fun setUp() {
        screensController = ScreensControllerImpl(TestRoutes.FirstScreen)
    }

    @Test
    fun initialRoutes() {
        screensController.routes shouldBe listOf(TestRoutes.FirstScreen)
    }

    @Test
    fun pushingSameRouteMultipleTimesThenPoppingUpToIt() {
        screensController.push(TestRoutes.SecondScreen)
        screensController.push(TestRoutes.ThirdScreen)
        screensController.push(TestRoutes.FourthScreen)
        screensController.push(TestRoutes.SecondScreen)
        screensController.push(TestRoutes.FifthScreen)
        screensController.routes shouldBe listOf(
            TestRoutes.FirstScreen,
            TestRoutes.SecondScreen,
            TestRoutes.ThirdScreen,
            TestRoutes.FourthScreen,
            TestRoutes.SecondScreen,
            TestRoutes.FifthScreen
        )
        screensController.pop(TestRoutes.SecondScreen)
        screensController.routes shouldBe listOf(
            TestRoutes.FirstScreen,
            TestRoutes.SecondScreen,
            TestRoutes.ThirdScreen,
            TestRoutes.FourthScreen,
            TestRoutes.SecondScreen
        )
        screensController.pop()
        screensController.pop(TestRoutes.SecondScreen)
        screensController.routes shouldBe listOf(
            TestRoutes.FirstScreen,
            TestRoutes.SecondScreen
        )
        screensController.pop()
        screensController.routes shouldBe listOf(
            TestRoutes.FirstScreen
        )
    }

    @Test
    fun poppingStartRoute() {
        shouldThrow<IllegalStateException> {
            screensController.pop()
        }
    }

    @Test
    fun poppingRouteThatIsNotOnTheStack() {
        shouldThrow<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen)
        }
    }

    @Test
    fun poppingToCurrentRoute() {
        screensController.push(TestRoutes.SecondScreen)
        shouldThrow<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen)
        }
    }
}
