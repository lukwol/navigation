package io.github.lukwol.navigation.screens.viewmodel.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController

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
