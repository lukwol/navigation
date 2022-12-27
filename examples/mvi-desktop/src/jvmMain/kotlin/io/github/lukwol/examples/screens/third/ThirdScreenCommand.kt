package io.github.lukwol.examples.screens.third

sealed class ThirdScreenCommand {
    data class UpdateText(val text: String) : ThirdScreenCommand()
}
