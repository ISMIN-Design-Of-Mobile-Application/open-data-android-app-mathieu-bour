package fr.mathieubour.fetedelascience

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.mathieubour.fetedelascience.data.Event
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.serialization.json.Json

private val LOAD_EVENT_REQUEST_CODE = "LOAD_EVENT_REQUEST_CODE";

class EventDetailsActivity : AppCompatActivity() {
    private lateinit var eventDetailsTitle: TextView
    private lateinit var eventDetailsImage: ImageView
    private lateinit var eventDetailsCity: TextView
    private lateinit var eventDetailsDates: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        eventDetailsTitle = findViewById(R.id.event_details_title)
        eventDetailsImage = findViewById(R.id.event_details_image)
        eventDetailsCity = findViewById(R.id.event_details_city)
        eventDetailsDates = findViewById(R.id.event_details_dates)

        val eventString = intent.getStringExtra(EXTRA_EVENT)
        val event: Event = Json.parse(Event.serializer(), eventString!!)

        fill(event)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    private fun fill(newSelectedEvent: Event) {
        eventDetailsTitle.text = newSelectedEvent.title
        Picasso.get().load(newSelectedEvent.image).into(eventDetailsImage)
        eventDetailsCity.text = newSelectedEvent.city

        val dates: String = if (newSelectedEvent.starts_at == newSelectedEvent.ends_at) {
            newSelectedEvent.starts_at
        } else {
            newSelectedEvent.starts_at + " - " + newSelectedEvent.ends_at
        }

        eventDetailsDates.text = dates
    }
}
