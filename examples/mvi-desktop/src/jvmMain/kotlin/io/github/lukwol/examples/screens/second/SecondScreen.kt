package io.github.lukwol.examples.screens.second

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.screens.navigation.LocalScreensController

@Composable
fun SecondScreen(
    state: SecondScreenViewState,
    commands: (SecondScreenCommand) -> Unit
) {
    val screensController = LocalScreensController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("SecondScreen")

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = state.text,
            onValueChange = { commands(SecondScreenCommand.UpdateText(it)) }
        )

        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { screensController.push(AppRoutes.thirdScreen) }
        ) {
            Text("Go to ThirdScreen")
        }
    }
}
