package io.github.lukwol.examples.screens.first

sealed interface FirstScreenCommand {
    class UpdateText(val text: String) : FirstScreenCommand
}
