package fr.mathieubour.fetedelascience.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import fr.mathieubour.fetedelascience.R
import fr.mathieubour.fetedelascience.data.Event
import java.lang.Exception

/**
 * Heavily used to list the Events in a RecyclerView in the ListFragment.
 * @see ListFragment
 * @see EventViewHolder
 */
class EventListAdapter(
    private var events: List<Event>,
    private val onClickListener: ((Event) -> Unit)
) : RecyclerView.Adapter<EventViewHolder>() {
    /**
     * Instantiate the view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        // create a new view
        val row = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_event, parent, false)

        return EventViewHolder(row)
    }

    /**
     * Push the data into the view holder.
     */
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.eventLoader.visibility = View.VISIBLE

        holder.eventTitle.text = event.name
        holder.eventCity.text = event.city
        Picasso.get().load(event.image).into(holder.eventImage, object: Callback{
            override fun onSuccess() {
                holder.eventLoader.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })

        // Set the provided click listener
        holder.rootView.setOnClickListener {
            onClickListener.invoke(event)
        }
    }

    override fun getItemCount() = events.size

    /**
     * Make a complete events replacement
     */
    fun replace(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }
}