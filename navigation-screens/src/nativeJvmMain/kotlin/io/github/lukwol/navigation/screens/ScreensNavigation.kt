package io.github.lukwol.navigation.screens

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    builder: ScreensMapBuilder.() -> Unit,
) {
    val mapBuilder = ScreensMapBuilder()
    builder(mapBuilder)

    val screensMap = remember { mapBuilder.build() }
    val screensController = remember { ScreensController(startRoute) }

    CompositionLocalProvider(
        LocalScreensController provides screensController,
    ) {
        val routeWithArgs = screensController.routesStack.last()

        Crossfade(
            targetState = routeWithArgs,
        ) { (route, args) ->
            screensMap.getValue(route)(args)
        }
    }
}
