package fr.mathieubour.fetedelascience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.mathieubour.fetedelascience.EXTRA_EVENT_ID
import fr.mathieubour.fetedelascience.EventDetailsActivity
import fr.mathieubour.fetedelascience.R
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.models.MainViewModel

/**
 * Holds the event list.
 */
class ListFragment : Fragment() {
    private lateinit var adapter: EventListAdapter
    private lateinit var eventsListObserver: Observer<List<Event>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        val model: MainViewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        // Setup event list observer
        eventsListObserver = Observer { newEvents -> adapter.replace(newEvents) }
        model.eventsList.observe(this, eventsListObserver)
    }

    /**
     * Using the EventListAdapter, setup the recycler view
     * @see EventListAdapter
     */
    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = activity!!.findViewById(R.id.event_list)
        adapter = EventListAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // click listener
        adapter.onClickListener = { event: Event ->
            val intent = Intent(activity, EventDetailsActivity::class.java)
            intent.putExtra(EXTRA_EVENT_ID, event.id)
            startActivity(intent)
        }
    }
}
