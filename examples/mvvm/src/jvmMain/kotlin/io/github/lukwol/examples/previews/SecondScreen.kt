package io.github.lukwol.examples.previews

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.lukwol.examples.screens.SecondScreen

@Preview
@Composable
private fun Preview() = MaterialTheme {
    SecondScreen("Hello World!")
}
