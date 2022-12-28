package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import kotlin.test.*

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
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen,
                TestRoutes.ThirdScreen,
                TestRoutes.FourthScreen,
                TestRoutes.SecondScreen,
                TestRoutes.FifthScreen
            ),
            screensController.routes
        )
        screensController.pop(TestRoutes.SecondScreen)
        assertEquals(
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen,
                TestRoutes.ThirdScreen,
                TestRoutes.FourthScreen,
                TestRoutes.SecondScreen
            ),
            screensController.routes
        )
        screensController.pop()
        screensController.pop(TestRoutes.SecondScreen)
        assertEquals(
            listOf(
                TestRoutes.FirstScreen,
                TestRoutes.SecondScreen
            ),
            screensController.routes
        )
        screensController.pop()
        assertEquals(
            listOf(TestRoutes.FirstScreen),
            screensController.routes
        )
    }

    @Test
    fun poppingStartRoute() {
        assertFailsWith<IllegalStateException> {
            screensController.pop().getOrThrow()
        }
    }

    @Test
    fun poppingRouteThatIsNotOnTheStack() {
        assertFailsWith<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen).getOrThrow()
        }
    }

    @Test
    fun poppingToCurrentRoute() {
        screensController.push(TestRoutes.SecondScreen)
        assertFailsWith<IllegalArgumentException> {
            screensController.pop(TestRoutes.SecondScreen).getOrThrow()
        }
    }
}
