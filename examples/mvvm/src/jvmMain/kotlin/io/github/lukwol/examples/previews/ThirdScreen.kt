package io.github.lukwol.examples.previews

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.lukwol.examples.screens.ThirdScreen
import io.github.lukwol.examples.screens.ThirdScreenViewModel

@Preview
@Composable
private fun Preview() = MaterialTheme {
    ThirdScreen(
        viewModel = ThirdScreenViewModel()
            .apply {
                texts = listOf(
                    "Hello World!",
                    "Goodbye World!",
                )
            },
    )
}
