package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.SupervisorJob

/**
 * Provides [viewModelScope] that is tied to specific **view**, it exposes the **view state** and encapsulates related **business logic**.
 *
 * [ViewModelScope] persists the **view state** when **view** is not currently active until **view** is dismissed.
 */
interface ViewModelScope {

    /**
     * Implementation of [ViewModelScope] based on [CoroutineScope]
     * that is [SupervisorJob] and bound to [MainCoroutineDispatcher.immediate].
     */
    class Delegate : ViewModelScope {
        override val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }

    /**
     * [CoroutineScope] tied to this [ViewModelScope].
     * This scope will be canceled when view connected to this [ViewModelScope] is cleared.
     */
    val viewModelScope: CoroutineScope
}
