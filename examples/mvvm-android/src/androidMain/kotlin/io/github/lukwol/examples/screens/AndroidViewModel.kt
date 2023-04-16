package io.github.lukwol.examples.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.lukwol.viewmodel.CoroutineScopeAware
import kotlinx.coroutines.CoroutineScope

abstract class AndroidViewModel : ViewModel(), CoroutineScopeAware {
    override val coroutineScope: CoroutineScope
        get() = viewModelScope
}
