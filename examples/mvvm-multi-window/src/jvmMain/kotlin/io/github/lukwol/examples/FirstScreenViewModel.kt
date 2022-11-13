package io.github.lukwol.examples

import androidx.compose.runtime.mutableStateOf
import io.github.lukwol.viewmodel.ViewModel

class FirstScreenViewModel : ViewModel() {
    var text = mutableStateOf("")
}
