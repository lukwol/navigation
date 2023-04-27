package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.viewmodel.ViewModel

class SecondScreenViewModel : ViewModel() {
    lateinit var firstText: String
    var secondText by mutableStateOf("")
}

@Composable
expect fun createSecondScreenViewModel(): SecondScreenViewModel
