package io.github.lukwol.viewmodel

/**
 * Abstract class that provides ready to use [ViewModelScope].
 *
 * Can be used as an alternative to [ViewModelScope] interface if there is no `ViewModel` superclass.
 */
abstract class ViewModel : ViewModelScope by ViewModelScope.Delegate()
