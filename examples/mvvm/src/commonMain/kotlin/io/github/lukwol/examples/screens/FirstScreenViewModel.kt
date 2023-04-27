package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class FirstScreenViewModel : ViewModel() {
    var text by mutableStateOf("")
}

@Composable
expect fun createFirstScreenViewModel(): FirstScreenViewModel
