package io.github.lukwol.screens.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.data.TestRoutes
import io.github.lukwol.screens.navigation.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun ThirdScreen(args: ThirdScreenArgs) {
    val screensController = LocalScreensController.current

    Column {
        Text(args.toString())
        Button(
            onClick = { screensController.pop() }
        ) {
            Text("Pop Screen")
        }
        Button(
            onClick = { screensController.pop(upToRoute = TestRoutes.FirstScreen) }
        ) {
            Text("Pop To First Screen")
        }
    }
}
