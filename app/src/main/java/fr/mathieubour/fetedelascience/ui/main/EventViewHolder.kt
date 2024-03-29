package fr.mathieubour.fetedelascience.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.mathieubour.fetedelascience.R

/**
 * Holds the list item representation of an Event
 */
class EventViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
    var eventImage: ImageView = rootView.findViewById(R.id.event_thumb)
    var eventLoader: ProgressBar = rootView.findViewById(R.id.event_loader)
    var eventTitle: TextView = rootView.findViewById(R.id.event_title)
    var eventCity: TextView = rootView.findViewById(R.id.event_city)
}