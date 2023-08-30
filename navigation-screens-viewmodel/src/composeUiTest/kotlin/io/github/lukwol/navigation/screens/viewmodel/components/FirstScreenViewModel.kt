package io.github.lukwol.navigation.screens.viewmodel.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.navigation.screens.viewmodel.ViewModel

class FirstScreenViewModel : ViewModel() {
    var text by mutableStateOf("")
}

@Composable
expect fun firstViewModel(): FirstScreenViewModel
