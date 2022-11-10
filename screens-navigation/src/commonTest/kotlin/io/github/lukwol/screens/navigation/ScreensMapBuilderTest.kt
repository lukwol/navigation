package io.github.lukwol.screens.navigation

import io.github.lukwol.screens.navigation.data.TestRoutes
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import kotlin.test.BeforeTest
import kotlin.test.Test

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
            build().keys shouldContainExactlyInAnyOrder listOf(TestRoutes.SecondScreen, TestRoutes.ThirdScreen)
        }
    }

    @Test
    fun screensWithNotUniqueRoutes() {
        with(screensMapBuilder) {
            screen(route = TestRoutes.SecondScreen, content = {})
            screen(route = TestRoutes.ThirdScreen, content = {})
            shouldThrow<IllegalArgumentException> {
                screen(route = TestRoutes.SecondScreen, content = {})
            }
        }
    }
}
