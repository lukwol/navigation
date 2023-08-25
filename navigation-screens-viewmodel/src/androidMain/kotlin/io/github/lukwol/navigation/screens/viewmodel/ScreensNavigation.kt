package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation as BasicScreensNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
actual fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentScope<*>.() -> EnterTransition),
    exitTransition: (AnimatedContentScope<*>.() -> ExitTransition),
    popEnterTransition: (AnimatedContentScope<*>.() -> EnterTransition),
    popExitTransition: (AnimatedContentScope<*>.() -> ExitTransition),
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
