package io.github.lukwol.examples

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensMapBuilder
import io.github.lukwol.navigation.screens.ScreensNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    ScreensNavigation(
        startRoute = AppRoutes.FirstScreenRoute,
        builder = ScreensMapBuilder::appNavigationContent,
    )
}
