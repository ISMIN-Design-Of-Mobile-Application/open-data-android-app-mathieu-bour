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
import fr.mathieubour.fetedelascience.EXTRA_EVENT
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

        // Setup recycler view
        val recyclerView: RecyclerView = activity!!.findViewById(R.id.event_list)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        adapter = EventListAdapter(emptyList())
        recyclerView.adapter = adapter

        val model: MainViewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        adapter.onClickListener = { event: Event ->
            val intent = Intent(activity, EventDetailsActivity::class.java)
            intent.putExtra(EXTRA_EVENT, event)
            startActivity(intent)
        }

        // Setup event list observer
        eventsListObserver = Observer { newEvents -> adapter.replace(newEvents) }
        model.eventsList.observe(this, eventsListObserver)
    }
}
