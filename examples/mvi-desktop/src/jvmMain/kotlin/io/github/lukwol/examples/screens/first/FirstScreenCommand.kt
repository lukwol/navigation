package io.github.lukwol.examples.screens.first

sealed class FirstScreenCommand {
    data class UpdateText(val text: String) : FirstScreenCommand()
}
