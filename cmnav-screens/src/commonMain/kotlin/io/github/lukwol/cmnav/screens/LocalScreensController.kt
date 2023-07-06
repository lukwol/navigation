package io.github.lukwol.cmnav.screens

import androidx.compose.runtime.compositionLocalOf

/**
 * Provides local [ScreensController] that can be used
 * for pushing or popping screens from within currently displayed screen.
 *
 * Defaults value is set to prevent problems with screens previews.
 */
val LocalScreensController = compositionLocalOf { ScreensController("") }
