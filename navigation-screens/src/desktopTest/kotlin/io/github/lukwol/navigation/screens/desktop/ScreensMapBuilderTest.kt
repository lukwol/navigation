package io.github.lukwol.navigation.screens.desktop

import io.github.lukwol.navigation.screens.ScreensMapBuilder
import io.github.lukwol.navigation.screens.desktop.data.TestRoutes
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class ScreensMapBuilderTest {

    private lateinit var screensMapBuilder: ScreensMapBuilder

    @BeforeTest
    fun setUp() {
        screensMapBuilder = ScreensMapBuilder()
    }

    @Test
    fun screensWithUniqueRoutes() {
        with(screensMapBuilder) {
            screen(route = TestRoutes.SecondScreen, content = {})
            screen(route = TestRoutes.ThirdScreen, content = {})
            assertContentEquals(listOf(TestRoutes.SecondScreen, TestRoutes.ThirdScreen), build().keys)
        }
    }

    @Test
    fun screensWithNotUniqueRoutes() {
        with(screensMapBuilder) {
            screen(route = TestRoutes.SecondScreen, content = {})
            screen(route = TestRoutes.ThirdScreen, content = {})
            assertFailsWith<IllegalArgumentException> {
                screen(route = TestRoutes.SecondScreen, content = {})
            }
        }
    }
}
