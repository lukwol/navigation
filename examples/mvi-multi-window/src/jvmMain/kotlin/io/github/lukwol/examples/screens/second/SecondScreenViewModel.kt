package io.github.lukwol.examples.screens.second

import io.github.lukwol.examples.MviViewModel

class SecondScreenViewModel(text: String) : MviViewModel<SecondScreenViewState, SecondScreenCommands>(
    initialState = SecondScreenViewState(text.ifEmpty { "No text passed" })
) {
    override fun onCommand(command: SecondScreenCommands) {}
}
