package io.github.lukwol.examples.screens.second

sealed class SecondScreenCommand {
    data class UpdateText(val text: String) : SecondScreenCommand()
}
