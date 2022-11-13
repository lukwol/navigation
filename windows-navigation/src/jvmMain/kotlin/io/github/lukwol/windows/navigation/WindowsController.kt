package io.github.lukwol.windows.navigation

import androidx.compose.runtime.mutableStateOf

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
    fun open(route: WindowRoute, arguments: WindowArguments? = null)

    /**
     * Closes window for the [WindowRoute] and removes it from the [routes] stack.
     *
     * @param route [WindowRoute] to close
     */
    fun close(route: WindowRoute)
}

/**
 * Actual implementation of the [WindowsController]
 */
class WindowsControllerImpl(startRoute: WindowRoute) : WindowsController {
    internal val routesState = mutableStateOf(setOf(WindowRouteWithArguments(startRoute)))

    override val routes get() = routesState.value.map(WindowRouteWithArguments::route).toSet()

    /**
     * Opens new window for the [WindowRoute] and adds it to the [routes] stack.
     *
     * @param route [WindowRoute] to open
     *
     * @throws IllegalArgumentException if window for [route] is already opened
     *
     * @see [WindowsController.open]
     */
    override fun open(route: WindowRoute, arguments: WindowArguments?) {
        if (route in routes) {
            throw IllegalArgumentException("Window for $route is already opened")
        } else {
            routesState.value += WindowRouteWithArguments(route, arguments)
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
    override fun close(route: WindowRoute) {
        if (route !in routes) {
            throw IllegalArgumentException("Window for $route is not opened")
        } else {
            routesState.value -= routesState.value.filter { it.route == route }
        }
    }
}

/**
 * No-op implementation of the [WindowsController]
 */
internal object WindowsControllerNoOp : WindowsController {
    override val routes: Set<WindowRoute> = emptySet()

    override fun open(route: WindowRoute, arguments: WindowArguments?) = Unit

    override fun close(route: WindowRoute) = Unit
}
