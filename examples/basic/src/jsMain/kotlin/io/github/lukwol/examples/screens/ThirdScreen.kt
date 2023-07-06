package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import io.github.lukwol.cmnav.screens.LocalScreensController
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.paddingLeft
import org.jetbrains.compose.web.css.paddingRight
import org.jetbrains.compose.web.css.paddingTop
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
actual fun ThirdScreen(args: List<String>?) {
    val screensController = LocalScreensController.current

    for (text in args.orEmpty()) {
        Div(
            attrs = {
                style {
                    paddingTop(20.px)
                    paddingLeft(20.px)
                    paddingRight(20.px)
                }
            },
        ) {
            Text(text)
        }
    }

    Div(
        attrs = {
            style {
                padding(20.px)
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
