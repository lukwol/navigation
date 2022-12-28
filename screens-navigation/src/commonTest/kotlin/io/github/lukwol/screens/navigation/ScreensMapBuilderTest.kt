package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class ScreensMapBuilderTest {

    private lateinit var basicScreensMapBuilder: BasicScreensMapBuilder

    @BeforeTest
    fun setUp() {
        basicScreensMapBuilder = BasicScreensMapBuilder()
    }

    @Test
    fun screensWithUniqueRoutes() {
        with(basicScreensMapBuilder) {
            screen(route = TestRoutes.SecondScreen, content = {})
            screen(route = TestRoutes.ThirdScreen, content = {})
            assertContentEquals(listOf(TestRoutes.SecondScreen, TestRoutes.ThirdScreen), build().keys)
        }
    }

    @Test
    fun screensWithNotUniqueRoutes() {
        with(basicScreensMapBuilder) {
            screen(route = TestRoutes.SecondScreen, content = {})
            screen(route = TestRoutes.ThirdScreen, content = {})
            assertFailsWith<IllegalArgumentException> {
                screen(route = TestRoutes.SecondScreen, content = {})
            }
        }
    }
}
