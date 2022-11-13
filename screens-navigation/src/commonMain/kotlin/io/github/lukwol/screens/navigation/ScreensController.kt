package io.github.lukwol.screens.navigation

import androidx.compose.runtime.mutableStateOf

/**
 * Manages navigation between the screens declared when building [ScreensNavigation].
 *
 * Available via [LocalScreensController].
 */
interface ScreensController {

    /**
     * List of current [routes][ScreenRoute] on the stack.
     *
     * For the last [route][ScreenRoute] screen will be displayed.
     */
    val routes: List<ScreenRoute>

    /**
     * Pushes new [ScreenRoute] on the [routes] stack.
     *
     * @param route [ScreenRoute] to navigate to
     * @param arguments optional [ScreenArguments] passed when navigating to [route]
     */
    fun push(route: ScreenRoute, arguments: ScreenArguments? = null)

    /**
     * Pops back to previous [ScreenRoute] on the [routes] stack.
     *
     * @param upToRoute if provided optionally pop multiple routes [upToRoute]
     */
    fun pop(upToRoute: ScreenRoute? = null)
}

/**
 * Actual implementation of the [ScreensController]
 */
internal class ScreensControllerImpl(startRoute: ScreenRoute) : ScreensController {
    internal val routesState = mutableStateOf(listOf(ScreenRouteWithArguments(startRoute)))

    override val routes get() = routesState.value.map(ScreenRouteWithArguments::route)

    override fun push(route: ScreenRoute, arguments: ScreenArguments?) {
        routesState.value += ScreenRouteWithArguments(route, arguments)
    }

    /**
     * Pops back to previous [ScreenRoute] on the [routes] stack.
     *
     * @param upToRoute if provided optionally pop multiple routes [upToRoute]
     *
     * @throws IllegalArgumentException if there is no [upToRoute] on the stack
     * or [upToRoute] is the last route on the stack
     * @throws IllegalStateException if it's about to pop last route on the [routes] stack
     *
     * @see [ScreensController.pop]
     */
    override fun pop(upToRoute: ScreenRoute?) {
        return when {
            upToRoute != null && upToRoute !in routes -> throw IllegalArgumentException("There is no $upToRoute on the stack")
            upToRoute == routes.last() -> throw IllegalArgumentException("Cannot pop up to current route $upToRoute")
            routes.size == 1 -> throw IllegalStateException("Cannot pop start route ${routes.first()}")
            upToRoute == null -> routesState.value = routesState.value.dropLast(1)
            else -> routesState.value = routesState.value.dropLastWhile { it.route != upToRoute }
        }
    }
}

/**
 * No-op implementation of the [ScreensController]
 */
internal object ScreensControllerNoOp : ScreensController {
    override val routes: List<ScreenRoute> = emptyList()

    override fun push(route: ScreenRoute, arguments: ScreenArguments?) = Unit

    override fun pop(upToRoute: ScreenRoute?) = Unit
}
