package io.github.lukwol.navigation.screens

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
