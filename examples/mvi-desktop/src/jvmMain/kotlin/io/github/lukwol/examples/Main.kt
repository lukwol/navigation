package io.github.lukwol.examples

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.singleWindowApplication

fun main() {
    singleWindowApplication {
        MaterialTheme {
            AppNavigation()
        }
    }
}
