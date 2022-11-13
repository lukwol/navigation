package io.github.lukwol.examples

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.windows.navigation.LocalWindowController

@Composable
fun FirstScreen(viewModel: FirstScreenViewModel) {
    val windowsController = LocalWindowController.current

    var text by viewModel.text

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = text,
            placeholder = {
                Text("Type something...")
            },
            onValueChange = { text = it }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                windowsController.open(AppRoutes.SecondWindowRoute, text)
            }
        ) {
            Text("Open second window")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    FirstScreen(viewModel = FirstScreenViewModel())
}
