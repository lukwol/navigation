package io.github.lukwol.navigation.screens.android.data

import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
class ThirdScreenArgs(
    val text: String,
    val number: Int,
) {
    override fun toString() = "text = $text, number = $number"
}
