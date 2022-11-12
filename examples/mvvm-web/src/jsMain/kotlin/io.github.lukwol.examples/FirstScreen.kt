package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import io.github.lukwol.screens.navigation.LocalScreensController
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextInput

@Composable
fun FirstScreen(viewModel: FirstScreenViewModel) {
    val screensController = LocalScreensController.current

    var text by viewModel.text

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        }
    ) {
        TextInput(value = text) {
            placeholder("Type something...")
            onInput {
                text = it.value
            }
        }
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
                    screensController.push(AppRoutes.SecondScreenRoute, text)
                }
            }
        ) {
            Text("Go to second screen")
        }
    }
}
