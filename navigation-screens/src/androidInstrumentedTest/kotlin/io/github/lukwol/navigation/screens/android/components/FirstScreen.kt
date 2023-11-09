package io.github.lukwol.navigation.screens.android.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.LocalScreensController
import io.github.lukwol.navigation.screens.android.data.TestRoutes

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
