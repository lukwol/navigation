package io.github.lukwol.examples

import io.github.lukwol.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("unused")
abstract class MviViewModel<S, C>(initialState: S) : ViewModel() {
    private val state = MutableStateFlow(initialState)

    fun stateAsFlow() = state.asStateFlow()

    fun dispatch(command: C) = viewModelScope.launch { onCommand(command) }

    protected abstract fun onCommand(command: C)

    protected fun <T> withState(block: (S) -> T) = block(state.value)

    protected fun setState(reducer: S.() -> S) {
        state.value = reducer(state.value)
    }
}
