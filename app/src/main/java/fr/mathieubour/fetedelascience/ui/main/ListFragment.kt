package fr.mathieubour.fetedelascience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
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
import java.util.*

/**
 * Holds the event list.
 */
class ListFragment : Fragment() {
    private lateinit var model: MainViewModel
    private lateinit var filter_toggle: ImageView
    private lateinit var search: EditText
    private lateinit var adapter: EventListAdapter
    private lateinit var eventsListObserver: Observer<List<Event>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    /**
     * 1. Setup the RecyclerView
     * 2. Setup the observer which will replace the RecyclerView list
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupSearch()
        setupRecyclerView()

        model = activity!!.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        }

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

        // Initialize the adapter with an empty list
        adapter = EventListAdapter(emptyList()) { event: Event ->
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra(EXTRA_EVENT_ID, event.id)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setupSearch() {
        filter_toggle = activity!!.findViewById(R.id.filter_toggle)
        search = activity!!.findViewById(R.id.search)

        filter_toggle.setOnClickListener {
            search.visibility = if (search.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        search.doAfterTextChanged {
            val filteredList = model.eventsList.value?.filter { event ->
                val query = search.text.toString().toLowerCase(Locale.getDefault())

                event.name.toLowerCase(Locale.getDefault()).contains(query)
                        || event.city.toLowerCase(Locale.getDefault()).contains(query)
            }

            filteredList?.let(adapter::replace)
        }
    }
}
