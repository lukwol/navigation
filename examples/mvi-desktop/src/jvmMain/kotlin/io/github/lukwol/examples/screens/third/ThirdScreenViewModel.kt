package io.github.lukwol.examples.screens.third

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class ThirdScreenViewModel : ViewModel() {

    var state by mutableStateOf(ThirdScreenViewState())
        private set

    fun onCommand(command: ThirdScreenCommand) {
        when (command) {
            is ThirdScreenCommand.UpdateText -> {
                state = state.copy(text = command.text)
            }
        }
    }
}
