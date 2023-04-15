package io.github.lukwol.examples

import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        AppNavigation()
    }
}
