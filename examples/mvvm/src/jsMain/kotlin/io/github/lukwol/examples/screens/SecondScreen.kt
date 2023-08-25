package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import io.github.lukwol.examples.AppRoutes
import io.github.lukwol.navigation.screens.LocalScreensController
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
actual fun SecondScreen(viewModel: SecondScreenViewModel) {
    val screensController = LocalScreensController.current

    Div(
        attrs = {
            style {
                padding(20.px)
            }
        },
    ) {
        Text(viewModel.firstText)
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
        TextInput(value = viewModel.secondText) {
            placeholder("Type something else...")
            onInput {
                viewModel.secondText = it.value
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
                        args = listOf(
                            viewModel.firstText,
                            viewModel.secondText,
                        ),
                    )
                }
            },
        ) {
            Text("Go to third screen")
        }
    }
}
