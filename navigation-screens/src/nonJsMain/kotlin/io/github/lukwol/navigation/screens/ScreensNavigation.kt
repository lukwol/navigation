package io.github.lukwol.navigation.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

/**
 * Defines screens navigation map by declaring all possible
 * [screens][ScreensMapBuilder.screen] and their contents identified by their routes.
 *
 * It's later provided via [compositionLocalOf] to allow navigation
 * to another screen within currently presented screen.
 *
 * Has separate implementations for Android and non Android targets.
 *
 * Android target underneath uses official **Compose Navigation** framework made by Google.
 * Android implementation utilizes optional route arguments
 * and JSON serialization via Kotlin Serialization.
 *
 * Other targets have common custom implementation that does not involve any serialization.
 *
 * @param startRoute first screen route for which the initial screen will be displayed
 * @param enterTransition defines enter transitions when pushing new screen on the stack,
 * by default uses fade in animation
 * @param exitTransition defines exit transitions when pushing new screen on the stack
 * by default uses fade out animation
 * @param popEnterTransition defines enter transitions when popping screen from the stack
 * by default uses animation specified in [enterTransition]
 * @param popExitTransition defines exit transitions when popping screen from the stack
 * by default uses animation specified in [exitTransition]
 * @param builder the builder used to construct the underlying screens navigation map
 *
 * @see LocalScreensController
 */
@Composable
expect fun ScreensNavigation(
    startRoute: String,
    enterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(700)) },
    exitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(700)) },
    popEnterTransition: (AnimatedContentTransitionScope<*>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<*>.() -> ExitTransition) =
        exitTransition,
    builder: ScreensMapBuilder.() -> Unit,
)
