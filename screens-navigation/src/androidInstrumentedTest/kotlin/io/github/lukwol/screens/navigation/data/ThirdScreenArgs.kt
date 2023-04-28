package io.github.lukwol.screens.navigation.data

import kotlinx.serialization.Serializable

@Serializable
class ThirdScreenArgs(
    val text: String,
    val number: Int,
) {
    override fun toString() = "text = $text, number = $number"
}
