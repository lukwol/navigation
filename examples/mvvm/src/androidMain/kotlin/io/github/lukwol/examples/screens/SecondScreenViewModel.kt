package io.github.lukwol.examples.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
actual fun createSecondScreenViewModel() = viewModel<SecondScreenViewModel>()
