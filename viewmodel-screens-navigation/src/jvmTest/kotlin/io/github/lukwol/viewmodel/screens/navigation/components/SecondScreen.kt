package io.github.lukwol.viewmodel.screens.navigation.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.viewmodel.ViewModel

class SecondScreenViewModel(initialText: String) : ViewModel() {
    var text = mutableStateOf(initialText)
}

@Suppress("TestFunctionName")
@Composable
fun SecondScreen(viewModel: SecondScreenViewModel) {
    val (text, setText) = viewModel.text

    val screensController = LocalScreensController.current

    TextField(
        value = text,
        onValueChange = setText
    )

    Button(
        onClick = { screensController.pop() }
    ) {
        Text("Pop Screen")
    }
}
