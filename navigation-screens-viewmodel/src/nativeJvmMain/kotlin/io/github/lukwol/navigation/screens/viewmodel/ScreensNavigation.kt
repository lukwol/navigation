package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
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
    val viewModelStore = remember { ViewModelStore() }

    BasicScreensNavigation(
        startRoute = startRoute,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        builder(VMScreensMapBuilder(viewModelStore, this))
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModelStore.values
                .map(ViewModel::coroutineScope)
                .forEach(CoroutineScope::cancel)
        }
    }
}
