package io.github.lukwol.examples.screens.second

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.lukwol.examples.screens.AndroidViewModel
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor() : AndroidViewModel() {
    var text: String = ""
}
