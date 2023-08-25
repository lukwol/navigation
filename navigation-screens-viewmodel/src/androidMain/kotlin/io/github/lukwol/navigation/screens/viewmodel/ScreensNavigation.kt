package io.github.lukwol.navigation.screens.viewmodel

import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation as BasicScreensNavigation

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    builder: VMScreensMapBuilder.() -> Unit,
) {
    BasicScreensNavigation(startRoute) {
        builder(VMScreensMapBuilder(this))
    }
}
