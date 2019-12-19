package fr.mathieubour.fetedelascience.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.mathieubour.fetedelascience.R

class EventViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
    var eventImage: ImageView = rootView.findViewById(R.id.event_thumb)
    var eventTitle: TextView = rootView.findViewById(R.id.event_title)
    var eventCity: TextView = rootView.findViewById(R.id.event_city)
}