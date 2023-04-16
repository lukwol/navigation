package io.github.lukwol.examples.screens.first

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.lukwol.examples.screens.AndroidViewModel
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor() : AndroidViewModel() {
    var text by mutableStateOf("")
}
