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
