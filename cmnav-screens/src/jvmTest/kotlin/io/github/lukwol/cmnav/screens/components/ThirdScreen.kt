package io.github.lukwol.cmnav.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.LocalScreensController
import io.github.lukwol.cmnav.screens.data.TestRoutes
import io.github.lukwol.cmnav.screens.data.ThirdScreenArgs

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
