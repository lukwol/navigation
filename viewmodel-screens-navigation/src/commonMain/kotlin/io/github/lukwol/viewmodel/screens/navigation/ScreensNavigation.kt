package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import io.github.lukwol.screens.navigation.ScreenRoute
import io.github.lukwol.viewmodel.ViewModel
import io.github.lukwol.viewmodel.ViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import io.github.lukwol.screens.navigation.ScreensNavigation as BasicScreensNavigation

/**
 * Declare screens navigation same as [ScreensNavigation][BasicScreensNavigation],
 * additionally allows to build [routes][ScreenRoute] by passing [ViewModel] in lambda.
 */
@Composable
fun ScreensNavigation(
    startRoute: ScreenRoute,
    animated: Boolean = false,
    builder: VMScreensMapBuilder.() -> Unit,
) {
    val viewModelStore = remember { ViewModelStore() }

    BasicScreensNavigation(startRoute, animated) {
        builder(VMScreensMapBuilder(viewModelStore, this))
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModelStore.values
                .map(ViewModelScope::viewModelScope)
                .forEach(CoroutineScope::cancel)
        }
    }
}
