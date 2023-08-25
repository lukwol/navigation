package io.github.lukwol.navigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

/**
 * Defines screens navigation map by declaring all possible
 * [screens][ScreensMapBuilder.screen] and their contents identified by their routes.
 *
 * It's later provided via [compositionLocalOf] to allow navigation
 * to another screen within currently presented screen.
 *
 * Has separate implementations for Android and non Android targets.
 *
 * Android target underneath uses official **Compose Navigation** framework made by Google.
 * Android implementation utilizes optional route arguments
 * and JSON serialization via Kotlin Serialization.
 *
 * Other targets have common custom implementation that does not involve any serialization.
 *
 * @param startRoute first screen route for which the initial screen will be displayed
 * @param builder the builder used to construct the underlying screens navigation map
 *
 * @see LocalScreensController
 */
@Composable
expect fun ScreensNavigation(
    startRoute: String,
    builder: ScreensMapBuilder.() -> Unit,
)
