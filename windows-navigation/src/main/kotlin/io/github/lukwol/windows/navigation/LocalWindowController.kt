package io.github.lukwol.windows.navigation

import androidx.compose.runtime.compositionLocalOf

/**
 *  [CompositionLocal][androidx.compose.runtime.CompositionLocal] of [WindowsController].
 *
 *  Defaults to [WindowsControllerNoOp] for easier creation of previews.
 */
val LocalWindowController = compositionLocalOf<WindowsController> { WindowsControllerNoOp }
