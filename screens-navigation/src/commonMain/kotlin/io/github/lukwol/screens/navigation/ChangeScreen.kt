package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

/**
 * Changes current screen
 *
 * @param route route to which screen should be changed
 * @param animated specifies if screen should be changed with `Crossfade` animation
 * @param content content of the screen
 */
@Composable
internal expect fun ChangeScreen(
    route: RouteWithArguments,
    animated: Boolean,
    content: @Composable (RouteWithArguments) -> Unit
)
