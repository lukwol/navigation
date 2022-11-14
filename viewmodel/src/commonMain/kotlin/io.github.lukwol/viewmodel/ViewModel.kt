package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * [ViewModel] is tied to specific **view**, it exposes the **view state** and encapsulates related **business logic**.
 *
 * [ViewModel] persists the **view state** when **view** is not currently active until **view** is dismissed.
 */
abstract class ViewModel {
    /**
     * [CoroutineScope] tied to this [ViewModel].
     * This scope will be canceled when view connected to this [ViewModel] is cleared
     *
     * This scope is bound to
     * [Dispatchers.Main.immediate][kotlinx.coroutines.MainCoroutineDispatcher.immediate]
     */
    val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}
