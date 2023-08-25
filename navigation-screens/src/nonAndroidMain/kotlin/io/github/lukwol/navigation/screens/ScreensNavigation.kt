package io.github.lukwol.navigation.screens

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
    val routesWithArguments = screensController.routesStack

    CompositionLocalProvider(
        LocalScreensController provides screensController,
    ) {
        ChangeScreen(
            route = routesWithArguments.last(),
        ) { (route, args) ->
            screensMap.getValue(route)(args)
        }
    }
}
