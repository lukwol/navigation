package io.github.lukwol.navigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

/**
 * Defines screens navigation map by declaring all possible
 * [screens][ScreensMapBuilder.screen] and their contents identified by their routes.
 *
 * It's later provided via [compositionLocalOf] to allow navigation
 * to another screen within currently presented screen.
 *
 * @param startRoute first screen route for which the initial screen will be displayed
 * @param builder the builder used to construct the underlying screens navigation map
 *
 * @see LocalScreensController
 */
@Composable
fun ScreensNavigation(
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
        val (route, args) = screensController.routesStack.last()
        screensMap.getValue(route)(args)
    }
}
