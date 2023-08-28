package io.github.lukwol.navigation.screens.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import io.github.lukwol.navigation.screens.ScreensNavigation
import io.github.lukwol.navigation.screens.data.TestRoutes
import io.github.lukwol.navigation.screens.data.ThirdScreenArgs

@Suppress("TestFunctionName")
@Composable
fun TestScreenNavigation() {
    ScreensNavigation(
        startRoute = TestRoutes.FirstScreen,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
        },
    ) {
        screen(TestRoutes.FirstScreen) {
            FirstScreen()
        }

        screen(TestRoutes.SecondScreen) { args: String? ->
            SecondScreen(args!!)
        }

        screen(TestRoutes.ThirdScreen) { args: ThirdScreenArgs? ->
            ThirdScreen(args!!)
        }
    }
}
