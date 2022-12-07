package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ScreensControllerTest {

    private lateinit var screensController: ScreensController

    @BeforeTest
    fun setUp() {
        screensController = ScreensControllerImpl(TestRoutes.FirstScreen)
    }

    @Test
    fun initialRoutes() {
        assertEquals(screensController.routes, listOf(TestRoutes.FirstScreen))
    }

    @Test
    fun pushingSameRouteMultipleTimesThenPoppingUpToIt() {
        screensController.push(TestRoutes.SecondScreen)
        screensController.push(TestRoutes.ThirdScreen)
        screensController.push(TestRoutes.FourthScreen)
        screensController.push(TestRoutes.SecondScreen)
        screensController.push(TestRoutes.FifthScreen)
        assertEquals(
            screensController.routes,
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen,
                TestRoutes.ThirdScreen,
                TestRoutes.FourthScreen,
                TestRoutes.SecondScreen,
                TestRoutes.FifthScreen
            )
        )
        screensController.pop(TestRoutes.SecondScreen)
        assertEquals(
            screensController.routes,
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen,
                TestRoutes.ThirdScreen,
                TestRoutes.FourthScreen,
                TestRoutes.SecondScreen
            )
        )
        screensController.pop()
        screensController.pop(TestRoutes.SecondScreen)
        assertEquals(
            screensController.routes,
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen
            )
        )
        screensController.pop()
        assertEquals(
            screensController.routes,
            listOf(
                TestRoutes.FirstScreen
            )
        )
    }

    @Test
    fun poppingStartRoute() {
        assertFailsWith<IllegalStateException> {
            screensController.pop()
        }
    }

    @Test
    fun poppingRouteThatIsNotOnTheStack() {
        assertFailsWith<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen)
        }
    }

    @Test
    fun poppingToCurrentRoute() {
        screensController.push(TestRoutes.SecondScreen)
        assertFailsWith<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen)
        }
    }
}
