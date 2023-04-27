package io.github.lukwol.viewmodel.screens.navigation

import androidx.lifecycle.SavedStateHandle
import io.github.lukwol.screens.navigation.NavArgsKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Accesses and decode navigation arguments from [SavedStateHandle].
 *
 * @param Args type of arguments that will be decoded from JSON
 * @return Decoded navigation arguments
 */
inline fun <reified Args> SavedStateHandle.navArgs(): Args? = get<String>(NavArgsKey)
    ?.let(Json.Default::decodeFromString)
