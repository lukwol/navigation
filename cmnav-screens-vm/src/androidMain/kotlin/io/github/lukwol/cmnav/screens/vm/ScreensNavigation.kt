package io.github.lukwol.cmnav.screens.vm

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.ScreensNavigation as BasicScreensNavigation

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    builder: VMScreensMapBuilder.() -> Unit,
) {
    BasicScreensNavigation(startRoute) {
        builder(VMScreensMapBuilder(this))
    }
}
