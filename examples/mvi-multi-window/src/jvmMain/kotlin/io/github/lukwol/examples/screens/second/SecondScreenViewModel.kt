package io.github.lukwol.examples.screens.second

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class SecondScreenViewModel(text: String) : ViewModel() {

    var state by mutableStateOf(SecondScreenViewState(text.ifEmpty { "No text passed" }))
        private set

    fun onCommand(command: SecondScreenCommand) {}
}
