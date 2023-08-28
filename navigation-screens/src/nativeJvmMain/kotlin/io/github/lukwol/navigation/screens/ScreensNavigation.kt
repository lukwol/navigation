package io.github.lukwol.navigation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember

@OptIn(ExperimentalAnimationApi::class)
@Composable
actual fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    exitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    popEnterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    popExitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    builder: ScreensMapBuilder.() -> Unit,
) {
    val mapBuilder = ScreensMapBuilder()
    builder(mapBuilder)

    val screensMap = remember { mapBuilder.build() }
    val screensController = remember { ScreensController(startRoute) }
    var previousRoute: String? = remember { null }

    CompositionLocalProvider(
        LocalScreensController provides screensController,
    ) {
        val routeWithArgs = screensController.routesStack.last()
        val navigationType = when (previousRoute) {
            in screensController.routes -> NavigationType.Push
            else -> NavigationType.Pop
        }
        AnimatedContent(
            targetState = routeWithArgs,
            transitionSpec = {
                when (navigationType) {
                    NavigationType.Push -> enterTransition() with exitTransition()
                    NavigationType.Pop -> popEnterTransition() with popExitTransition()
                }
            },
            content = { (route, args) ->
                screensMap.getValue(route)(args)
            },
        )

        SideEffect {
            previousRoute = routeWithArgs.route
        }
    }
}
