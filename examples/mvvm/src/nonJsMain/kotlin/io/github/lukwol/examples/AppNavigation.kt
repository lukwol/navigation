package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.screens.viewmodel.VMScreensMapBuilder

@Composable
fun AppNavigation() {
    ScreensNavigation(
        startRoute = AppRoutes.FirstScreenRoute,
        builder = VMScreensMapBuilder::appNavigationContent,
    )
}
