package io.github.lukwol.examples

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.screens.viewmodel.VMScreensMapBuilder

@Composable
fun AppNavigation() {
    ScreensNavigation(
        startRoute = AppRoutes.FirstScreenRoute,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        },
        builder = VMScreensMapBuilder::appNavigationContent,
    )
}
