package io.github.lukwol.navigation.windows

import androidx.compose.runtime.mutableStateListOf

/**
 * Manages navigation between the windows declared when building [WindowsNavigation].
 *
 * Available via [LocalWindowController].
 */
class WindowsController(startRoute: String) {

    /**
     * Mutable list of presented windows and their arguments.
     *
     * Routes can be added to the list by opening new windows,
     * and removed from the list by closing windows.
     *
     * Whenever content of the list changes, opened windows are changed.
     */
    internal val routesList = mutableStateListOf(RouteWithArgs(startRoute))

    /**
     * Read only list of current window routes along with their navigation arguments,
     * the list is based on the [routesList].
     *
     * @see routesList
     */
    val routes get() = routesList.map(RouteWithArgs::route).toSet()

    /**
     * Opens new window for the [route] and adds it to the [routes] stack.
     * Content of the windows is based on what was declared in [WindowsNavigation].
     *
     * @param route for which new window will be opened
     *
     * @return [Result.success] if window was opened successfully or [Result.failure] with an error:
     *
     * [IllegalArgumentException] if window for the [route] is already opened.
     *
     * When there was an error, error opened windows do not change.
     */
    fun open(route: String, args: Any? = null): Result<Unit> = runCatching {
        if (route in routes) {
            throw IllegalArgumentException("Window for $route is already opened")
        } else {
            routesList += RouteWithArgs(route, args)
        }
    }

    /**
     * Closes the window for given [route] and removes it from the [routes] list.
     * When there was an error, error opened windows do not change.
     *
     * @param route for which currently opened window will be closed
     *
     * @return [Result.success] if window was closed successfully or [Result.failure] with an error:
     *
     * [IllegalArgumentException] if window for the [route] was not opened.
     *
     * When there was an error, error opened windows do not change.
     *
     *  @see [WindowsController.close]
     */
    fun close(route: String): Result<Unit> = runCatching {
        if (route !in routes) {
            throw IllegalArgumentException("Window for $route is not opened")
        } else {
            routesList.removeAll { it.route == route }
        }
    }
}
