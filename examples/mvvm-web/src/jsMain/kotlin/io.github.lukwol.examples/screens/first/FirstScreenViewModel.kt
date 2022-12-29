package io.github.lukwol.examples.screens.first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class FirstScreenViewModel : ViewModel() {
    var text by mutableStateOf("")
}
