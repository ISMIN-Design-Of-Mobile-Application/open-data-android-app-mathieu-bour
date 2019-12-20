package fr.mathieubour.fetedelascience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.data.EventsDatabase
import kotlinx.android.synthetic.main.activity_event_details.*

/**
 * Display the details of an event
 */
class EventDetailsActivity : AppCompatActivity() {
    private lateinit var gMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        event_details_map.onCreate(savedInstanceState)
        event_details_map.onResume()
        MapsInitializer.initialize(applicationContext)

        val eventId = intent.getStringExtra(EXTRA_EVENT_ID)
            ?: throw Exception("$EXTRA_EVENT_ID must be defined when loading ${this.javaClass.canonicalName}")

        val event = EventsDatabase.getInstance(this)
            .eventDao()
            .find(intent.getStringExtra(EXTRA_EVENT_ID)!!)
            ?: throw Exception("Event with id $eventId was not found in the local database")

        fill(event)
    }

    private fun fill(event: Event) {
        event_details_name.text = event.name
        Picasso.get().load(event.image).into(event_details_image)
        event_details_organization.text =
            getString(R.string.event_details_organization, event.organization)
        event_details_dates.text = event.dates
        event_details_conditions.text = event.conditions
        event_details_description.text = event.description
        event_details_address.text = event.address
        event_details_location_description.text = event.location_description

        if (this::gMap.isInitialized) {
            // If the map is already initialized we can synchronously load the marker
            setupMapMarker(event)
        } else {
            // Else, we have to delay it
            event_details_map.getMapAsync {
                gMap = it
                setupMapMarker(event)
            }
        }

    }

    /**
     * Move the map and set the marker to the current event position
     */
    private fun setupMapMarker(event: Event) {
        gMap.clear()

        val marker = MarkerOptions()
            .position(event.position)
            .title(event.title)
            .snippet(event.snippet)

        gMap.addMarker(marker)
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(event.position, 15.0f))
    }
}
