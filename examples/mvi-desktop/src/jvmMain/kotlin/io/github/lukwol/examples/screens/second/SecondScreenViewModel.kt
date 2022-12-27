package io.github.lukwol.examples.screens.second

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class SecondScreenViewModel : ViewModel() {

    var state by mutableStateOf(SecondScreenViewState())
        private set

    fun onCommand(command: SecondScreenCommand) {
        when (command) {
            is SecondScreenCommand.UpdateText -> {
                state = state.copy(text = command.text)
            }
        }
    }
}
