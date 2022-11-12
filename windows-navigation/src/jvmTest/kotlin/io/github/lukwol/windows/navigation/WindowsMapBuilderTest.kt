package io.github.lukwol.windows.navigation

import androidx.compose.ui.window.Window
import io.github.lukwol.windows.navigation.data.TestRoutes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.Before
import org.junit.Test

class WindowsMapBuilderTest {

    private lateinit var windowsMapBuilder: WindowsMapBuilder

    @Before
    fun setUp() {
        windowsMapBuilder = WindowsMapBuilder()
    }

    @Test
    fun `windows with unique routes`() {
        with(windowsMapBuilder) {
            window(TestRoutes.FirstWindow) {}
            window(TestRoutes.SecondWindow) {}
            build().keys shouldContainExactlyInAnyOrder listOf(TestRoutes.FirstWindow, TestRoutes.SecondWindow)
        }
    }

    @Test
    fun `windows with not unique routes`() {
        with(windowsMapBuilder) {
            window(TestRoutes.FirstWindow) {}
            window(TestRoutes.SecondWindow) {}
            shouldThrow<IllegalArgumentException> {
                window(TestRoutes.FirstWindow) {}
            }
        }
    }

    @Test
    fun `custom window`() {
        with(windowsMapBuilder) {
            window(
                route = TestRoutes.FirstWindow,
                windowFactory = {
                    Window(
                        onCloseRequest = { }
                    ) {}
                }
            ) {}
            build().keys shouldContainExactlyInAnyOrder listOf(TestRoutes.FirstWindow)
        }
    }
}
