package io.github.lukwol.examples.di

import io.github.lukwol.examples.screens.first.FirstScreenViewModel
import io.github.lukwol.examples.screens.second.SecondScreenViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

object ViewModelProvider : KoinComponent {
    fun getFirstScreenViewModel(): FirstScreenViewModel = get()
    fun getSecondScreenViewModel(text: String): SecondScreenViewModel = get(parameters = { parametersOf(text) })
}
