package io.github.lukwol.examples.screens.first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.navigation.screens.viewmodel.ViewModel

class FirstScreenViewModel : ViewModel() {
    var state by mutableStateOf(FirstScreenViewState())
        private set

    fun onCommand(command: FirstScreenCommand) {
        when (command) {
            is FirstScreenCommand.UpdateText -> {
                state = state.copy(text = command.text)
            }
        }
    }
}
