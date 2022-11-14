package io.github.lukwol.examples.screens.first

import io.github.lukwol.examples.MviViewModel

class FirstScreenViewModel : MviViewModel<FirstScreenViewState, FirstScreenCommands>(
    initialState = FirstScreenViewState()
) {
    override fun onCommand(command: FirstScreenCommands) {
        when (command) {
            is FirstScreenCommands.UpdateText -> setState {
                copy(text = command.text)
            }
        }
    }
}
