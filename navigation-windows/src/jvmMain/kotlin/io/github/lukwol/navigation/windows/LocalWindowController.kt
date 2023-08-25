package io.github.lukwol.navigation.windows

import androidx.compose.runtime.compositionLocalOf

/**
 * Provides local [WindowsController] that can be used
 * for opening or closing screens from within currently displayed screens
 * in any window.
 *
 * Defaults value is set to prevent problems with screens previews.
 */
val LocalWindowController = compositionLocalOf { WindowsController("") }
