package fr.mathieubour.fetedelascience.data

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val title: String,
    val city: String,
    val starts_at: String,
    val ends_at: String,
    val permalink: String,
    val thumb: String,
    val image: String,
    val latitude: Float,
    val longitude: Float
)