package io.github.lukwol.screens.navigation

import androidx.compose.runtime.compositionLocalOf

/**
 * Provides local [ScreensController] that can be used
 * for pushing or popping screens from within currently displayed screen.
 *
 * Defaults value is set to prevent problems with screens previews.
 */
val LocalScreensController = compositionLocalOf { ScreensController("") }
