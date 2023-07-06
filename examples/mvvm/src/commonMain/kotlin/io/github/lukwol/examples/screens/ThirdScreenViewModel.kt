package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import io.github.lukwol.viewmodel.screens.navigation.ViewModel

class ThirdScreenViewModel : ViewModel() {
    lateinit var texts: List<String>
}

@Composable
expect fun createThirdScreenViewModel(): ThirdScreenViewModel
