package io.github.lukwol.examples.screens.second

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.windows.navigation.LocalWindowController

@Suppress("UNUSED_PARAMETER")
@Composable
fun SecondScreen(
    state: SecondScreenViewState,
    commands: (SecondScreenCommands) -> Unit
) {
    val windowsController = LocalWindowController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(state.text)

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                windowsController.close(AppRoutes.SecondWindowRoute)
            }
        ) {
            Text("Close window")
        }
    }
}

@Preview
@Composable
private fun Preview() = MaterialTheme {
    SecondScreen(
        state = SecondScreenViewState(text = "Hello World!"),
        commands = {}
    )
}
