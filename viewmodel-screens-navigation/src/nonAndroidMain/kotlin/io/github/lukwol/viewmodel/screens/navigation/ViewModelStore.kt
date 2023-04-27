package io.github.lukwol.viewmodel.screens.navigation

import io.github.lukwol.viewmodel.ViewModel

/**
 * View model store is responsible for persisting [view models][ViewModel] state,
 * for screens that are not currently displayed but are on the navigation stack.
 *
 * @property viewModels underlying mutable map
 * that registers [view models][ViewModel] for screen routes
 */
@PublishedApi
internal class ViewModelStore private constructor(
    private val viewModels: MutableMap<String, ViewModel>,
) : MutableMap<String, ViewModel> by viewModels {
    constructor() : this(mutableMapOf())
}
