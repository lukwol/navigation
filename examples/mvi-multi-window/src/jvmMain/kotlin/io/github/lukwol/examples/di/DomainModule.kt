package io.github.lukwol.examples.di

import io.github.lukwol.examples.screens.first.FirstScreenViewModel
import io.github.lukwol.examples.screens.second.SecondScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    factoryOf(::FirstScreenViewModel)
    factoryOf(::SecondScreenViewModel)
}
