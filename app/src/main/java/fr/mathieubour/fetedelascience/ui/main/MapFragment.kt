package fr.mathieubour.fetedelascience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.maps.android.clustering.ClusterManager
import fr.mathieubour.fetedelascience.EXTRA_EVENT
import fr.mathieubour.fetedelascience.EventDetailsActivity
import fr.mathieubour.fetedelascience.R
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.models.MainViewModel


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMapView: MapView
    private lateinit var mMap: GoogleMap
    private lateinit var mClusterManager: ClusterManager<Event>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)

        mMapView = rootView.findViewById(R.id.map) as MapView
        mMapView.onCreate(savedInstanceState)
        mMapView.onResume() // display map immediately, ref: https://stackoverflow.com/a/19354359

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.getMapAsync(this::onMapReady)

        return rootView
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        mMap.isMyLocationEnabled = true

        val model: MainViewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        setupClusterManager()

        val eventsListObserver = Observer { newEvents: List<Event> -> placeEventMarkers(newEvents) }
        model.getEventsList().observe(this, eventsListObserver)
    }

    private fun setupClusterManager() {
        mClusterManager = ClusterManager(activity, mMap)
        mMap.setOnCameraIdleListener(mClusterManager)

        mClusterManager.setOnClusterItemClickListener { event: Event? ->
            if (event != null) {
                val intent = Intent(activity, EventDetailsActivity::class.java)
                intent.putExtra(EXTRA_EVENT, event)
                startActivity(intent)
            }

            true
        }

        mMap.setOnMarkerClickListener(mClusterManager)
    }

    private fun placeEventMarkers(events: List<Event>) {
        mClusterManager.addItems(events)
    }
}
