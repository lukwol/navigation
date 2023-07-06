package io.github.lukwol.cmnav.screens

import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: RouteWithArgs,
    content: @Composable (RouteWithArgs) -> Unit,
) = content(route)
