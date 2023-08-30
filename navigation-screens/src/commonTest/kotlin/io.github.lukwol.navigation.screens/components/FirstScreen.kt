package io.github.lukwol.navigation.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.data.TestRoutes

@Suppress("TestFunctionName")
@Composable
fun FirstScreen() {
    val screensController = LocalScreensController.current

    Button(
        onClick = { screensController.push(TestRoutes.SecondScreen, "Foo") },
    ) {
        Text("Push Second Screen")
    }
}
