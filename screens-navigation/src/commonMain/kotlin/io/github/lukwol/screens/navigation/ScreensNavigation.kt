package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

/**
 * Declare screens navigation by defining all [screens][BasicScreensMapBuilder.screen] for [routes][ScreenRoute].
 *
 * Provides [LocalScreensController] that can be used for navigation inside each screen.
 *
 * @param startRoute first [route][ScreenRoute] for which screen will be displayed
 * @param animated enables `Crossfade` animation (currently works only on jvm)
 * @param builder the builder used to construct the screens navigation map
 */
@Composable
fun ScreensNavigation(
    startRoute: ScreenRoute,
    animated: Boolean = false,
    builder: BasicScreensMapBuilder.() -> Unit
) {
    val mapBuilder = BasicScreensMapBuilder()
    builder(mapBuilder)

    val screensMap = remember { mapBuilder.build() }
    val screensController = remember { ScreensControllerImpl(startRoute) }
    val routesWithArguments = screensController.routesState

    CompositionLocalProvider(
        LocalScreensController provides screensController
    ) {
        ChangeScreen(
            route = routesWithArguments.last(),
            animated = animated
        ) { (route, arguments) ->
            screensMap.getValue(route)(arguments)
        }
    }
}
