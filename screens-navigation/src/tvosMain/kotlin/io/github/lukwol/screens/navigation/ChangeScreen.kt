package io.github.lukwol.screens.navigation

import androidx.compose.runtime.Composable

@Composable
internal actual fun ChangeScreen(
    route: ScreenRouteWithArguments,
    animated: Boolean,
    content: @Composable (ScreenRouteWithArguments) -> Unit
) = content(route)
