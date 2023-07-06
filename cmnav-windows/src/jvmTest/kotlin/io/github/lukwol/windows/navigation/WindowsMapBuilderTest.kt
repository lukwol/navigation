package io.github.lukwol.windows.navigation

import androidx.compose.ui.window.Window
import io.github.lukwol.windows.navigation.data.TestRoutes
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

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
            assertContentEquals(build().keys, listOf(TestRoutes.FirstWindow, TestRoutes.SecondWindow))
        }
    }

    @Test
    fun `windows with not unique routes`() {
        with(windowsMapBuilder) {
            window(TestRoutes.FirstWindow) {}
            window(TestRoutes.SecondWindow) {}
            assertFailsWith<IllegalArgumentException> {
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
                        onCloseRequest = { },
                    ) {}
                },
            ) {}
            assertContentEquals(build().keys, listOf(TestRoutes.FirstWindow))
        }
    }
}
