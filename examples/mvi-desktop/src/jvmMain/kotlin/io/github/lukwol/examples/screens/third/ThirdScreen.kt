package io.github.lukwol.examples.screens.third

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.screens.navigation.LocalScreensController

@Composable
fun ThirdScreen(
    state: ThirdScreenViewState,
    commands: (ThirdScreenCommand) -> Unit,
) {
    val screensController = LocalScreensController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(),
    ) {
        Text("ThirdScreen")

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = state.text,
            onValueChange = { commands(ThirdScreenCommand.UpdateText(it)) },
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { screensController.pop() },
        ) {
            Text("Go back")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { screensController.pop(upToRoute = AppRoutes.firstScreen) },
        ) {
            Text("Go back to FirstScreen")
        }
    }
}
