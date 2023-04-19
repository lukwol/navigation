package io.github.lukwol.examples

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController

@Suppress("FunctionName", "unused")
fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
        AppNavigation()
    }
}
