package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import io.github.lukwol.screens.navigation.LocalScreensController
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun SecondScreen(viewModel: SecondScreenViewModel) {
    val screensController = LocalScreensController.current

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        },
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
        },
    ) {
        Button(
            attrs = {
                onClick {
                    screensController.pop()
                }
            },
        ) {
            Text("Go back")
        }
    }
}
