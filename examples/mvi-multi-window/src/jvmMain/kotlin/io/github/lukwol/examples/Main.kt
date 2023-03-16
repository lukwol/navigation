package io.github.lukwol.examples

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.application
import io.github.lukwol.examples.di.appModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun main() {
    startKoin {
        printLogger(Level.INFO)
        modules(
            appModule,
        )
    }
    application {
        MaterialTheme {
            AppNavigation()
        }
    }
}
