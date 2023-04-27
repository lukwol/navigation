package io.github.lukwol.screens.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: RouteWithArgs,
    content: @Composable (RouteWithArgs) -> Unit,
) = Crossfade(
    targetState = route,
    content = content,
)