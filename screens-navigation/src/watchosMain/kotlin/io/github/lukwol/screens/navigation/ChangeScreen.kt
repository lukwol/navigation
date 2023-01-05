package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: RouteWithArguments,
    animated: Boolean,
    content: @Composable (RouteWithArguments) -> Unit
) = content(route)
