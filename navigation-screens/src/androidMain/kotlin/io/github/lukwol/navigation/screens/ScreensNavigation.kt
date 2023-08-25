package io.github.lukwol.navigation.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
actual fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentScope<*>.() -> EnterTransition),
    exitTransition: (AnimatedContentScope<*>.() -> ExitTransition),
    popEnterTransition: (AnimatedContentScope<*>.() -> EnterTransition),
    popExitTransition: (AnimatedContentScope<*>.() -> ExitTransition),
    builder: ScreensMapBuilder.() -> Unit,
) {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalScreensController provides ScreensController(navController),
    ) {
        NavHost(
            navController = navController,
            startDestination = appendNavArgs(startRoute),
        ) {
            val mapBuilder = ScreensMapBuilder(this)
            builder(mapBuilder)
        }
    }
}
