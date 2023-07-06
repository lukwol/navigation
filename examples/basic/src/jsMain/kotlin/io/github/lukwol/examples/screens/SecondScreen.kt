package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.lukwol.cmnav.screens.LocalScreensController
import io.github.lukwol.examples.AppRoutes
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
actual fun SecondScreen(args: String?) {
    val screensController = LocalScreensController.current

    val firstText = args
        .orEmpty()
        .ifEmpty { "No text passed" }

    var secondText by remember { mutableStateOf("") }

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        },
    ) {
        Text(firstText)
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
        TextInput(value = secondText) {
            placeholder("Type something else...")
            onInput {
                secondText = it.value
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
                    screensController.pop()
                }
            },
        ) {
            Text("Go back")
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
                    screensController.push(
                        route = AppRoutes.ThirdScreenRoute,
                        args = listOf(firstText, secondText),
                    )
                }
            },
        ) {
            Text("Go to third screen")
        }
    }
}
