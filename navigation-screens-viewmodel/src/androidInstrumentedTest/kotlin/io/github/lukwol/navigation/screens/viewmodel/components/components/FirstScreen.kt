package io.github.lukwol.navigation.screens.viewmodel.components.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.viewmodel.components.data.TestRoutes

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
