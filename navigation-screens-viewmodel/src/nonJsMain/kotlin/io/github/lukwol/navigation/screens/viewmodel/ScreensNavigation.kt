package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
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
@OptIn(ExperimentalAnimationApi::class)
@Composable
expect fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentScope<*>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: (AnimatedContentScope<*>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: (AnimatedContentScope<*>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: (AnimatedContentScope<*>.() -> ExitTransition) =
        exitTransition,
    builder: VMScreensMapBuilder.() -> Unit,
)
