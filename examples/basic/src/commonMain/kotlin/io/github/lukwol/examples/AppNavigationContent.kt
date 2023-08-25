package io.github.lukwol.examples

import io.github.lukwol.examples.screens.FirstScreen
import io.github.lukwol.examples.screens.SecondScreen
import io.github.lukwol.examples.screens.ThirdScreen
import io.github.lukwol.navigation.screens.ScreensMapBuilder

fun ScreensMapBuilder.appNavigationContent() {
    screen(AppRoutes.FirstScreenRoute) {
        FirstScreen()
    }

    screen(AppRoutes.SecondScreenRoute) { args: String? ->
        SecondScreen(args)
    }

    screen(AppRoutes.ThirdScreenRoute) { args: List<String>? ->
        ThirdScreen(args)
    }
}
