package io.github.lukwol.windows.navigation

import io.github.lukwol.windows.navigation.data.TestRoutes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.Before
import org.junit.Test

class WindowsControllerTest {

    private lateinit var windowsController: WindowsController

    @Before
    fun setUp() {
        windowsController = WindowsControllerImpl(TestRoutes.FirstWindow)
    }

    @Test
    fun `initial routes`() {
        windowsController.routes shouldBe listOf(TestRoutes.FirstWindow)
    }

    @Test
    fun `opening and closing multiple windows`() {
        windowsController.open(TestRoutes.SecondWindow)
        windowsController.routes shouldBe setOf(
            TestRoutes.FirstWindow,
            TestRoutes.SecondWindow
        )

        windowsController.open(TestRoutes.ThirdWindow)
        windowsController.routes shouldBe setOf(
            TestRoutes.FirstWindow,
            TestRoutes.SecondWindow,
            TestRoutes.ThirdWindow
        )

        windowsController.close(TestRoutes.FirstWindow)
        windowsController.routes shouldBe setOf(
            TestRoutes.SecondWindow,
            TestRoutes.ThirdWindow
        )

        windowsController.close(TestRoutes.SecondWindow)
        windowsController.routes shouldBe setOf(TestRoutes.ThirdWindow)
    }

    @Test
    fun `opening window that is already opened`() {
        windowsController.open(TestRoutes.SecondWindow)
        shouldThrow<IllegalArgumentException> {
            windowsController.open(TestRoutes.SecondWindow)
        }
    }

    @Test
    fun `closing window that is not opened`() {
        shouldThrow<IllegalArgumentException> {
            windowsController.close(TestRoutes.SecondWindow)
        }
    }
}
