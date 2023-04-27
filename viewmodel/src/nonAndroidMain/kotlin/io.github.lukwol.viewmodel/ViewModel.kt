package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

actual abstract class ViewModel {

    actual val coroutineScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Main.immediate,
    )
}
