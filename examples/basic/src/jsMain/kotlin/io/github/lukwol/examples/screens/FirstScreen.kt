package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.screens.navigation.LocalScreensController
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingBottom
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextInput

@Composable
actual fun FirstScreen() {
    val screensController = LocalScreensController.current

    var text by remember { mutableStateOf("") }

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        },
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
        },
    ) {
        Button(
            attrs = {
                onClick {
                    screensController.push(AppRoutes.SecondScreenRoute, text)
                }
            },
        ) {
            Text("Go to second screen")
        }
    }
}
