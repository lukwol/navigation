package io.github.lukwol.examples.screens.first

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.windows.navigation.LocalWindowController

@Composable
fun FirstScreen(
    state: FirstScreenViewState,
    commands: (FirstScreenCommand) -> Unit
) {
    val windowsController = LocalWindowController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = state.text,
            placeholder = {
                Text("Type something...")
            },
            onValueChange = {
                commands(FirstScreenCommand.UpdateText(it))
            }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                windowsController.open(AppRoutes.SecondWindowRoute, state.text)
            }
        ) {
            Text("Open second window")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    FirstScreen(
        state = FirstScreenViewState(),
        commands = {}
    )
}
