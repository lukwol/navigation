package io.github.lukwol.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.SupervisorJob

/**
 * Abstract class that provides ready to use [coroutineScope].
 */
abstract class ViewModel : CoroutineScopeAware {

    /**
     * Implementation of [CoroutineScopeAware] based on [CoroutineScope]
     * that is [SupervisorJob] and bound to [MainCoroutineDispatcher.immediate].
     */
    override val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
}
