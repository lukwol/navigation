package io.github.lukwol.viewmodel.screens.navigation

import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.ScreensNavigation as BasicScreensNavigation

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    builder: VMScreensMapBuilder.() -> Unit,
) {
    BasicScreensNavigation(startRoute) {
        builder(VMScreensMapBuilder(this))
    }
}
