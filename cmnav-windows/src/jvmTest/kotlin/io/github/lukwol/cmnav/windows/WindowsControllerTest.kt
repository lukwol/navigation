package io.github.lukwol.cmnav.windows

import io.github.lukwol.cmnav.windows.data.TestRoutes
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class WindowsControllerTest {

    private lateinit var windowsController: WindowsController

    @Before
    fun setUp() {
        windowsController = WindowsController(TestRoutes.FirstWindow)
    }

    @Test
    fun `initial routes`() {
        assertEquals(windowsController.routes, setOf(TestRoutes.FirstWindow))
    }

    @Test
    fun `opening and closing multiple windows`() {
        windowsController.open(TestRoutes.SecondWindow)
        assertEquals(
            windowsController.routes,
            setOf(
                TestRoutes.FirstWindow,
                TestRoutes.SecondWindow,
            ),
        )

        windowsController.open(TestRoutes.ThirdWindow)
        assertEquals(
            windowsController.routes,
            setOf(
                TestRoutes.FirstWindow,
                TestRoutes.SecondWindow,
                TestRoutes.ThirdWindow,
            ),
        )

        windowsController.close(TestRoutes.FirstWindow)
        assertEquals(
            windowsController.routes,
            setOf(
                TestRoutes.SecondWindow,
                TestRoutes.ThirdWindow,
            ),
        )

        windowsController.close(TestRoutes.SecondWindow)
        assertEquals(windowsController.routes, setOf(TestRoutes.ThirdWindow))
    }

    @Test
    fun `opening window that is already opened`() {
        windowsController.open(TestRoutes.SecondWindow)
        assertFailsWith<IllegalArgumentException> {
            windowsController.open(TestRoutes.SecondWindow).getOrThrow()
        }
    }

    @Test
    fun `closing window that is not opened`() {
        assertFailsWith<IllegalArgumentException> {
            windowsController.close(TestRoutes.SecondWindow).getOrThrow()
        }
    }
}
