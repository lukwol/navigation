package io.github.lukwol.examples

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import io.github.lukwol.examples.screens.first.FirstScreen
import io.github.lukwol.examples.screens.first.FirstScreenViewModel
import io.github.lukwol.examples.screens.second.SecondScreen
import io.github.lukwol.examples.screens.second.SecondScreenViewModel
import io.github.lukwol.navigation.screens.viewmodel.ScreensNavigation
import io.github.lukwol.navigation.windows.LocalWindowController
import io.github.lukwol.navigation.windows.WindowsNavigation
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    WindowsNavigation(
        startRoute = AppRoutes.FirstWindowRoute,
    ) {
        window(
            route = AppRoutes.FirstWindowRoute,
            title = "First Window",
        ) {
            ScreensNavigation(
                startRoute = AppRoutes.FirstScreenRoute,
            ) {
                screen(
                    route = AppRoutes.FirstScreenRoute,
                    viewModelFactory = { koinInject<FirstScreenViewModel>() },
                ) { viewModel ->
                    FirstScreen(
                        state = viewModel.state,
                        commands = viewModel::onCommand,
                    )
                }
            }
        }

        window(
            route = AppRoutes.SecondWindowRoute,
            windowFactory = {
                val windowsController = LocalWindowController.current
                Window(
                    title = "Custom Window",
                    onCloseRequest = {
                        windowsController.close(AppRoutes.SecondWindowRoute)
                    },
                    alwaysOnTop = true,
                    content = it,
                )
            },
        ) { windowArgs: String? ->
            ScreensNavigation(
                startRoute = AppRoutes.SecondScreenRoute,
            ) {
                screen(
                    route = AppRoutes.SecondScreenRoute,
                    viewModelFactory = { koinInject<SecondScreenViewModel> { parametersOf(windowArgs) } },
                ) { viewModel ->
                    SecondScreen(
                        state = viewModel.state,
                        commands = viewModel::onCommand,
                    )
                }
            }
        }
    }
}
