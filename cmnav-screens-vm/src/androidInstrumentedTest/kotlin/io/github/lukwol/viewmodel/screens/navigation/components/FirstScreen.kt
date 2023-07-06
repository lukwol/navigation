package io.github.lukwol.viewmodel.screens.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.viewmodel.screens.navigation.ViewModel
import io.github.lukwol.viewmodel.screens.navigation.data.TestRoutes

class FirstScreenViewModel : ViewModel() {
    var text by mutableStateOf("")
}

@Suppress("TestFunctionName")
@Composable
fun FirstScreen(viewModel: FirstScreenViewModel) {
    val screensController = LocalScreensController.current

    Column {
        TextField(
            value = viewModel.text,
            onValueChange = { viewModel.text = it },
        )

        Button(
            onClick = { screensController.push(TestRoutes.SecondScreen, viewModel.text) },
        ) {
            Text("Push Second Screen")
        }
    }
}
