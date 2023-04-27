package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import io.github.lukwol.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import io.github.lukwol.screens.navigation.ScreensNavigation as BasicScreensNavigation

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    builder: VMScreensMapBuilder.() -> Unit,
) {
    val viewModelStore = remember { ViewModelStore() }

    BasicScreensNavigation(startRoute) {
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
