package io.github.lukwol.examples.screens.first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.cmnav.screens.vm.ViewModel

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
