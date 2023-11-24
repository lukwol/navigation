package io.github.lukwol.navigation.screens.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel as AndroidViewModel

actual abstract class ViewModel : AndroidViewModel() {
    actual val coroutineScope get() = viewModelScope
}
