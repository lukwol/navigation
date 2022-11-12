package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.LocalScreensController
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun SecondScreen(viewModel: SecondScreenViewModel) {
    val screensController = LocalScreensController.current

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        }
    ) {
        Text(viewModel.text)
    }

    Div(
        attrs = {
            style {
                paddingLeft(20.px)
                paddingRight(20.px)
                paddingBottom(20.px)
            }
        }
    ) {
        Button(
            attrs = {
                onClick {
                    screensController.pop()
                }
            }
        ) {
            Text("Go back")
        }
    }
}
