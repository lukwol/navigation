package io.github.lukwol.cmnav.screens

/**
 * Manages navigation between the screens declared when building [ScreensNavigation].
 *
 * Available via [LocalScreensController].
 *
 * Has separate implementations for Android and non Android targets.
 *
 * @see ScreensNavigation
 */
expect class ScreensController(startRoute: String) {

    /**
     * Pushes new screen route on the routes stack.
     * Effectively it changes current screen to the new screen for given [route]
     * which are declared when building [ScreensNavigation].
     *
     * @param route route for which new screen will be navigated to
     * @param args optional screen arguments passed when navigating to [route]
     */
    inline fun <reified Args> push(route: String, args: Args)

    /**
     * Pushes new screen route on the routes stack without passing arguments.
     * Effectively it changes current screen to the new screen for given [route]
     * which are declared when building [ScreensNavigation].
     *
     * @param route route for which new screen will be navigated to
     */
    fun push(route: String)

    /**
     * Pops back to previous screen route on the routes stack.
     *
     * @param upToRoute if provided optionally pops multiple routes up to given [upToRoute].
     *
     * @return [Result.success] if screen was popped successfully or [Result.failure] with an error:
     *
     * [IllegalArgumentException] if there is no [upToRoute] on the stack
     * or [upToRoute] is the last route on the stack.
     *
     * [IllegalStateException] if it's about to pop last route on the routes stack.
     *
     * When there was an error, error screens do not change on the stack.
     */
    fun pop(upToRoute: String? = null): Result<Unit>
}
