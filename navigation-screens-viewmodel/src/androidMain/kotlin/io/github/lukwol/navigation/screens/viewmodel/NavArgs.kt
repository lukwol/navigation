package io.github.lukwol.navigation.screens.viewmodel

import androidx.lifecycle.SavedStateHandle
import io.github.lukwol.navigation.screens.NavArgsKey
import kotlinx.serialization.json.Json

/**
 * Accesses and decode navigation arguments from [SavedStateHandle].
 *
 * @param Args type of arguments that will be decoded from JSON
 * @return Decoded navigation arguments
 */
@Suppress("unused")
inline fun <reified Args> SavedStateHandle.navArgs(): Args? =
    get<String>(NavArgsKey)
        ?.let(Json.Default::decodeFromString)
