package io.github.lukwol.cmnav.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

actual open class ScreensMapBuilder(
    val navGraphBuilder: NavGraphBuilder,
) {
    actual inline fun <reified Args> screen(
        route: String,
        noinline content: @Composable (args: Args?) -> Unit,
    ) {
        navGraphBuilder.composable(
            route = appendNavArgs(route),
        ) { navBackStackEntry ->
            val args: Args? = navBackStackEntry
                .arguments
                ?.getString(NavArgsKey)
                ?.let(Json.Default::decodeFromString)
            content(args)
        }
    }

    actual fun screen(
        route: String,
        content: @Composable () -> Unit,
    ) = screen<Unit>(
        route = route,
        content = { content() },
    )
}
