package fr.mathieubour.fetedelascience.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.io.Serializable

/**
 * A "Fête de la science" event.
 */
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

    /**
     * Get the position in LatLng for Google Maps ClusterManager
     * @see LatLng
     */
    override fun getPosition(): LatLng {
        return LatLng(latitude, longitude)
    }

    /**
     * The Google Maps marker title
     */
    override fun getTitle(): String {
        return name
    }

    /**
     * The snippet, aka the description of the generated Google Maps markers
     */
    override fun getSnippet(): String {
        return description
    }
}