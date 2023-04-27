package io.github.lukwol.screens.navigation

import androidx.compose.runtime.mutableStateListOf

actual class ScreensController actual constructor(startRoute: String) {

    /**
     * Mutable list that serves as a stack of presented screens and their arguments.
     *
     * Routes with args can be pushed on and popped from the stack.
     *
     * Whenever last route is changed, currently presented screen is changed.
     */
    @PublishedApi
    internal val routesStack = mutableStateListOf(RouteWithArgs(startRoute))

    /**
     * Read only list of current screen routes along with their navigation arguments,
     * the stack is based on `routesStack`
     *
     * @see routesStack
     */
    val routes get() = routesStack.map(RouteWithArgs::route)

    @Suppress("NOTHING_TO_INLINE")
    actual inline fun <Args> push(route: String, args: Args) {
        routesStack += RouteWithArgs(route, args)
    }

    actual fun push(route: String) {
        routesStack += RouteWithArgs(route, null)
    }

    actual fun pop(upToRoute: String?): Result<Unit> = runCatching {
        when {
            upToRoute != null && upToRoute !in routes -> throw IllegalArgumentException("There is no $upToRoute on the stack")
            upToRoute == routes.last() -> throw IllegalArgumentException("Cannot pop up to current route $upToRoute")
            routes.size == 1 -> throw IllegalStateException("Cannot pop start route ${routes.first()}")
            upToRoute == null -> routesStack.removeLast()
            else -> while (routes.last() != upToRoute) routesStack.removeLast()
        }
    }
}
