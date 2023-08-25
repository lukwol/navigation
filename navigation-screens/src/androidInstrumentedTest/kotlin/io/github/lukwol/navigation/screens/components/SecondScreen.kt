package io.github.lukwol.navigation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.data.TestRoutes
import io.github.lukwol.navigation.screens.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun SecondScreen(args: String) {
    val screensController = LocalScreensController.current

    Column {
        Text(args)
        Button(
            onClick = {
                screensController.push(
                    TestRoutes.ThirdScreen,
                    ThirdScreenArgs(text = "Bar", number = 42),
                )
            },
        ) {
            Text("Push Third Screen")
        }
    }
}
