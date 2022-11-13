package io.github.lukwol.examples

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.screens.navigation.LocalScreensController

@Composable
fun SecondScreen(text: String) {
    val screensController = LocalScreensController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text.ifEmpty { "No text passed" })

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                screensController.pop()
            }
        ) {
            Text("Go back")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    SecondScreen("Hello World!")
}
