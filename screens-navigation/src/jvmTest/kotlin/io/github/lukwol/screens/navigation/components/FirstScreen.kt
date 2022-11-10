package io.github.lukwol.screens.navigation.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.LocalScreensController
import io.github.lukwol.screens.navigation.data.TestRoutes

@Suppress("TestFunctionName")
@Composable
fun FirstScreen() {
    val screensController = LocalScreensController.current

    Button(
        onClick = { screensController.push(TestRoutes.SecondScreen, "Foo") }
    ) {
        Text("Push Second Screen")
    }
}
