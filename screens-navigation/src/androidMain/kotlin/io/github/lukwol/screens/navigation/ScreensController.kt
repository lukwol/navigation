package io.github.lukwol.screens.navigation

import androidx.navigation.NavHostController
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class ScreensController(
    val navController: NavHostController?,
) {

    actual constructor(
        startRoute: String,
    ) : this(null)

    actual inline fun <reified Args> push(route: String, args: Args) {
        navController?.navigate(
            route = "$route?$NavArgsKey=${Json.Default.encodeToString(args)}",
        )
    }

    actual fun push(route: String) {
        navController?.navigate(route)
    }

    actual fun pop(upToRoute: String?) = if (upToRoute == null) {
        navController?.popBackStack()
    } else {
        navController?.popBackStack(
            route = appendNavArgs(upToRoute),
            inclusive = false,
        )
    }.let {
        when (it) {
            true -> Result.success(Unit)
            else -> Result.failure(
                RuntimeException("Could not pop the back stack"),
            )
        }
    }
}
