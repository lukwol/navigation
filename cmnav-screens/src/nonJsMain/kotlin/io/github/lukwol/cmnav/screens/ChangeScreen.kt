package io.github.lukwol.cmnav.screens

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: io.github.lukwol.cmnav.screens.RouteWithArgs,
    content: @Composable (io.github.lukwol.cmnav.screens.RouteWithArgs) -> Unit,
) = Crossfade(
    targetState = route,
    content = content,
)
