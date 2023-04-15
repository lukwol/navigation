package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope

/**
 * Provides [coroutineScope] that is tied to specific **view**, it exposes the **view state** and encapsulates related **business logic**.
 *
 * [coroutineScope] persists the **view state** when **view** is not currently active until **view** is dismissed.
 *
 * Can be used as an alternative to [ViewModel] class, when abstract class cannot be used.
 */
interface CoroutineScopeAware {

    /**
     * [CoroutineScope] tied to this [CoroutineScopeAware] implementation.
     * This scope will be canceled when view connected to this [CoroutineScopeAware] implementation is cleared.
     */
    val coroutineScope: CoroutineScope
}
