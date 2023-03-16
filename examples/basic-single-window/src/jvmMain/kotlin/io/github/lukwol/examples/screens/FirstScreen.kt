package io.github.lukwol.examples.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.screens.navigation.LocalScreensController

@Composable
fun FirstScreen() {
    val screensController = LocalScreensController.current

    var text by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            value = text,
            placeholder = {
                Text("Type something...")
            },
            onValueChange = { text = it },
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                screensController.push(AppRoutes.SecondScreenRoute, text)
            },
        ) {
            Text("Go to second screen")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    FirstScreen()
}
