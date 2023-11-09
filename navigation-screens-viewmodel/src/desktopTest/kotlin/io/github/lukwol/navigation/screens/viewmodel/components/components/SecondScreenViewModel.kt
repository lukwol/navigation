package io.github.lukwol.navigation.screens.viewmodel.components.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.navigation.screens.viewmodel.ViewModel

class SecondScreenViewModel : ViewModel() {
    var text: String? by mutableStateOf(null)
}
