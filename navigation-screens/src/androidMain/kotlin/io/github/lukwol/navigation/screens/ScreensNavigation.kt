package io.github.lukwol.navigation.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
actual fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    exitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    popEnterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition),
    popExitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition),
    builder: ScreensMapBuilder.() -> Unit,
) {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalScreensController provides ScreensController(navController),
    ) {
        NavHost(
            navController = navController,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition,
            startDestination = appendNavArgs(startRoute),
        ) {
            val mapBuilder = ScreensMapBuilder(this)
            builder(mapBuilder)
        }
    }
}
