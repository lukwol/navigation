package io.github.lukwol.navigation.screens.data

import kotlinx.serialization.Serializable

@Serializable
class ThirdScreenArgs(
    val text: String,
    val number: Int,
) {
    override fun toString() = "text = $text, number = $number"
}