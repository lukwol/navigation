package io.github.lukwol.screens.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: ScreenRouteWithArguments,
    animated: Boolean,
    content: @Composable (ScreenRouteWithArguments) -> Unit
) = when {
    animated -> Crossfade(
        targetState = route,
        content = content
    )
    else -> content(route)
}
