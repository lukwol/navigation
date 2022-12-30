package io.github.lukwol.windows.navigation

import androidx.compose.runtime.mutableStateListOf

/**
 * Manages navigation between the windows declared when building [WindowsNavigation].
 *
 * Available via [LocalWindowController].
 */
interface WindowsController {

    /**
     * List of current [routes][WindowRoute] on the stack.
     *
     * For the last [route][WindowRoute] window will be displayed.
     */
    val routes: Set<WindowRoute>

    /**
     * Opens new window for the [WindowRoute] and adds it to the [routes] stack.
     *
     * @param route [WindowRoute] to open
     * @param arguments optional [WindowArguments] passed when navigating to [route]
     */
    fun open(route: WindowRoute, arguments: WindowArguments? = null): Result<Unit>

    /**
     * Closes window for the [WindowRoute] and removes it from the [routes] stack.
     *
     * @param route [WindowRoute] to close
     */
    fun close(route: WindowRoute): Result<Unit>
}

/**
 * Actual implementation of the [WindowsController]
 */
class WindowsControllerImpl(startRoute: WindowRoute) : WindowsController {
    internal val routesState = mutableStateListOf(WindowRouteWithArguments(startRoute))

    override val routes get() = routesState.map(WindowRouteWithArguments::route).toSet()

    /**
     * Opens new window for the [WindowRoute] and adds it to the [routes] stack.
     *
     * @param route [WindowRoute] to open
     *
     * @throws IllegalArgumentException if window for [route] is already opened
     *
     * @see [WindowsController.open]
     */
    override fun open(route: WindowRoute, arguments: WindowArguments?): Result<Unit> = runCatching {
        if (route in routes) {
            throw IllegalArgumentException("Window for $route is already opened")
        } else {
            routesState += WindowRouteWithArguments(route, arguments)
        }
    }

    /**
     * Closes window for the [WindowRoute] and removes it from the [routes] stack.
     *
     * @param route [WindowRoute] to close
     *
     * @throws IllegalArgumentException if window for [route] is not opened
     *
     * @see [WindowsController.close]
     */
    override fun close(route: WindowRoute): Result<Unit> = runCatching {
        if (route !in routes) {
            throw IllegalArgumentException("Window for $route is not opened")
        } else {
            routesState.removeAll { it.route == route }
        }
    }
}

/**
 * No-op implementation of the [WindowsController]
 */
internal object WindowsControllerNoOp : WindowsController {
    override val routes: Set<WindowRoute> = emptySet()

    override fun open(route: WindowRoute, arguments: WindowArguments?) = Result.success(Unit)

    override fun close(route: WindowRoute) = Result.success(Unit)
}
