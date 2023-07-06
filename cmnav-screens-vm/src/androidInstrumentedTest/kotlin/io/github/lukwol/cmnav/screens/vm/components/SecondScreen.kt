package io.github.lukwol.cmnav.screens.vm.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.cmnav.screens.LocalScreensController
import io.github.lukwol.cmnav.screens.vm.ViewModel

class SecondScreenViewModel : ViewModel() {
    var text: String? by mutableStateOf(null)
}

@Suppress("TestFunctionName")
@Composable
fun SecondScreen(viewModel: SecondScreenViewModel) {
    val screensController = LocalScreensController.current

    Column {
        TextField(
            value = viewModel.text.orEmpty(),
            onValueChange = { viewModel.text = it },
        )

        Button(
            onClick = { screensController.pop() },
        ) {
            Text("Pop Screen")
        }
    }
}
