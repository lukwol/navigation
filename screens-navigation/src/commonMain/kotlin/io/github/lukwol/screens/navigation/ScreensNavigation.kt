package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

/**
 * Declare screens navigation by defining all [screens][ScreensMapBuilder.screen] for [routes][ScreenRoute].
 *
 * Provides [LocalScreensController] that can be used for navigation inside each screen.
 *
 * @param startRoute first [route][ScreenRoute] for which screen will be displayed
 * @param builder the builder used to construct the screens navigation map
 */
@Composable
fun ScreensNavigation(
    startRoute: ScreenRoute,
    builder: ScreensMapBuilder.() -> Unit
) {
    val mapBuilder = ScreensMapBuilder()
    builder(mapBuilder)

    val screensMap = remember { mapBuilder.build() }
    val screensController = remember { ScreensControllerImpl(startRoute) }

    val routesWithArguments by screensController.routesState
    val (route, arguments) = routesWithArguments.last()

    CompositionLocalProvider(
        LocalScreensController provides screensController
    ) {
        screensMap.getValue(route)(arguments)
    }
}
