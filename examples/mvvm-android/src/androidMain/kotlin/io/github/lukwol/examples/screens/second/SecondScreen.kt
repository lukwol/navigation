package io.github.lukwol.examples.screens.second

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.lukwol.screens.navigation.LocalScreensController

@Composable
fun SecondScreen(viewModel: SecondScreenViewModel) {
    val screensController = LocalScreensController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(viewModel.text.ifEmpty { "No text passed" })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { screensController.pop() },
        ) {
            Text("Go back")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    SecondScreen(
        viewModel = SecondScreenViewModel()
            .apply { text = "Hello World!" },
    )
}
