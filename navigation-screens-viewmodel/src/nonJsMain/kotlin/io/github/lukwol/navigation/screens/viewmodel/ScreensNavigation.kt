package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation as BasicScreensNavigation

/**
 * Declare screens navigation same as [ScreensNavigation][BasicScreensNavigation]
 * but additionally allows declaration of screens with [ViewModel].
 *
 * Has separate implementations for Android and non Android targets.
 *
 * Both implementation uses [ScreensNavigation] underneath and adds support for lifecycle aware [ViewModel].
 *
 * Android target utilizes official ViewModel class from **Lifecycle** framework to leverage
 * view models persistence, management and make them lifecycle aware.
 *
 * Other targets share same [ViewModel] implementation and takes care
 * of cancelling [ViewModel.coroutineScope] when screens or windows are disposed.
 */
@Composable
expect fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
        exitTransition,
    builder: VMScreensMapBuilder.() -> Unit,
)
