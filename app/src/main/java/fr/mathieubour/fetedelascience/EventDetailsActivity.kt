package fr.mathieubour.fetedelascience

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.mathieubour.fetedelascience.data.Event
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {
    private lateinit var eventDetailsName: TextView
    private lateinit var eventDetailsImage: ImageView
    private lateinit var eventDetailsOrganization: TextView
    private lateinit var eventDetailsDates: TextView
    private lateinit var eventDetailsConditions: TextView
    private lateinit var eventDetailsDescription: TextView
    private lateinit var eventDetailsAddress: TextView
    private lateinit var eventDetailsLocationDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        eventDetailsName = findViewById(R.id.event_details_name)
        eventDetailsImage = findViewById(R.id.event_details_image)
        eventDetailsOrganization = findViewById(R.id.event_details_organization)
        eventDetailsDates = findViewById(R.id.event_details_dates)
        eventDetailsAddress = findViewById(R.id.event_details_address)
        eventDetailsConditions = findViewById(R.id.event_details_conditions)
        eventDetailsDescription = findViewById(R.id.event_details_description)
        eventDetailsAddress = findViewById(R.id.event_details_address)
        eventDetailsLocationDescription = findViewById(R.id.event_details_location_description)

        fill(intent.getSerializableExtra(EXTRA_EVENT) as Event)
    }

    private fun fill(newSelectedEvent: Event) {
        eventDetailsName.text = newSelectedEvent.name
        Picasso.get().load(newSelectedEvent.image).into(eventDetailsImage)
        eventDetailsOrganization.text = getString(R.string.event_details_organization, newSelectedEvent.organization)
        eventDetailsDates.text = newSelectedEvent.dates
        eventDetailsConditions.text = newSelectedEvent.conditions
        eventDetailsDescription.text = newSelectedEvent.description
        eventDetailsAddress.text = newSelectedEvent.address
        event_details_location_description.text = newSelectedEvent.location_description
    }
}
