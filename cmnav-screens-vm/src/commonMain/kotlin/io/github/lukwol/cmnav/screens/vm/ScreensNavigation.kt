package io.github.lukwol.cmnav.screens.vm

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.ScreensNavigation as BasicScreensNavigation

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
    builder: VMScreensMapBuilder.() -> Unit,
)
