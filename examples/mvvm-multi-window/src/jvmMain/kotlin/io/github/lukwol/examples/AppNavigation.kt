package io.github.lukwol.examples

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import io.github.lukwol.screens.navigation.ScreensNavigation
import io.github.lukwol.viewmodel.screens.navigation.screen
import io.github.lukwol.windows.navigation.LocalWindowController
import io.github.lukwol.windows.navigation.WindowsNavigation

@Composable
fun AppNavigation() {
    WindowsNavigation(
        startRoute = AppRoutes.FirstWindowRoute
    ) {
        window(
            route = AppRoutes.FirstWindowRoute,
            title = "First Window"
        ) {
            ScreensNavigation(
                startRoute = AppRoutes.FirstScreenRoute
            ) {
                screen(
                    route = AppRoutes.FirstScreenRoute,
                    viewModelFactory = { FirstScreenViewModel() }
                ) { viewModel ->
                    FirstScreen(viewModel)
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
                    content = it
                )
            }
        ) { windowArguments ->
            ScreensNavigation(
                startRoute = AppRoutes.SecondScreenRoute
            ) {
                screen(
                    route = AppRoutes.SecondScreenRoute,
                    viewModelFactory = { SecondScreenViewModel(windowArguments as String) }
                ) { viewModel ->
                    SecondScreen(viewModel)
                }
            }
        }
    }
}
