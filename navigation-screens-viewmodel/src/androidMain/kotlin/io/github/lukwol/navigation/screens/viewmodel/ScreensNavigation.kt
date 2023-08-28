package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation as BasicScreensNavigation

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    exitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    popEnterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    popExitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    builder: VMScreensMapBuilder.() -> Unit,
) {
    BasicScreensNavigation(
        startRoute = startRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        builder(VMScreensMapBuilder(this))
    }
}
