package io.github.lukwol.screens.navigation

import androidx.lifecycle.SavedStateHandle

/**
 * Key for which JSON encoded navigation arguments are stored in [SavedStateHandle]
 * and are appended to screen route.
 */
const val NavArgsKey = "navArgs"

/**
 * Screen route to which optional navigation arguments can be appended.
 */
@PublishedApi
internal fun appendNavArgs(screenRoute: String): String = "$screenRoute?$NavArgsKey={$NavArgsKey}"
