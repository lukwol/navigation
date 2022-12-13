package io.github.lukwol.screens.navigation

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 *  [CompositionLocal] of [ScreensController].
 *
 *  Defaults to [ScreensControllerNoOp] for easier creation of previews.
 */
val LocalScreensController = compositionLocalOf<ScreensController> { ScreensControllerNoOp }
