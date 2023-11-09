package io.github.lukwol.navigation.screens.desktop.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.desktop.data.TestRoutes
import io.github.lukwol.navigation.screens.desktop.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun ThirdScreen(args: ThirdScreenArgs) {
    val screensController = LocalScreensController.current

    Column {
        Text(args.toString())
        Button(
            onClick = { screensController.pop() },
        ) {
            Text("Pop Screen")
        }
        Button(
            onClick = { screensController.pop(upToRoute = TestRoutes.FirstScreen) },
        ) {
            Text("Pop To First Screen")
        }
    }
}
