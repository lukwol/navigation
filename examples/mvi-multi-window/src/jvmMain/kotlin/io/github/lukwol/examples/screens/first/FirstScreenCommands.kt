package io.github.lukwol.examples.screens.first

sealed interface FirstScreenCommands {
    class UpdateText(val text: String) : FirstScreenCommands
}
