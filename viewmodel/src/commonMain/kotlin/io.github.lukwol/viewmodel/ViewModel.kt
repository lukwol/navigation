package io.github.lukwol.viewmodel

/**
 * Abstract class that provides ready to use [ViewModelScope].
 */
abstract class ViewModel : ViewModelScope by ViewModelScope.Delegate()
