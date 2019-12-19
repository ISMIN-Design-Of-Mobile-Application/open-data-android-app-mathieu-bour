package fr.mathieubour.fetedelascience.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.io.Serializable

data class Event(
    val id: String,
    val permalink: String,
    // what
    val name: String,
    val description: String,
    val conditions: String,
    val image: String,
    val thumb: String,
    // who
    val organization: String,
    val telephone: String,
    // where
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val location_description: String,
    // when
    val dates: String
) : Serializable, ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun getSnippet(): String {
        return description
    }

    override fun getTitle(): String {
        return name
    }
}