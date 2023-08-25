package io.github.lukwol.navigation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
actual fun ScreensNavigation(
    startRoute: String,
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
