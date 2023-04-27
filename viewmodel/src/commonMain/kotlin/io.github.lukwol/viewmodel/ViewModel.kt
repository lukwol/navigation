package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope

/**
 * [ViewModel] is tied to specific view, it exposes the view state and encapsulates related business logic.
 *
 *  When used in a combination with screens and/or windows navigation, [ViewModel] is tightly integrated
 *  into screens and windows navigation, by exposing lifecycle aware [coroutineScope],
 *  that is canceled whenever screen or window is dismissed.
 *
 * [ViewModel] allows to persists the view state when screen is not currently active until it's dismissed.
 *
 * [ViewModel] separate implementations for Android and non Android targets.
 *
 * On Android [ViewModel] inherits from official Android `ViewModel` class and [coroutineScope]
 * is just another way to reference `viewModelScope`.
 *
 * Other targets have common implementation where `ViewModel` does not inherit from any class
 * and creates it's own new `coroutineScope` when instantiating this class.
 */
expect abstract class ViewModel() {

    /**
     * This scope will be canceled when view connected to this CoroutineScopeAware implementation is cleared.
     */
    val coroutineScope: CoroutineScope
}
