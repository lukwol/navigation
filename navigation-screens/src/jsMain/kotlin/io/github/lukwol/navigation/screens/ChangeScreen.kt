package io.github.lukwol.navigation.screens

import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: RouteWithArgs,
    content: @Composable (RouteWithArgs) -> Unit,
) = content(route)
