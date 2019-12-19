package fr.mathieubour.fetedelascience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import fr.mathieubour.fetedelascience.EXTRA_EVENT_ID
import fr.mathieubour.fetedelascience.EventDetailsActivity
import fr.mathieubour.fetedelascience.R
import fr.mathieubour.fetedelascience.data.Event
import fr.mathieubour.fetedelascience.models.MainViewModel

/**
 * Holds the Google Map and manage the marker clustering.
 */
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

    /**
     * Called when the Google Map is ready.
     */
    override fun onMapReady(map: GoogleMap) {
        val franceLatLng = LatLng(46.2276, 2.2137)

        mMap = map
        mMap.isMyLocationEnabled = false

        val model: MainViewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        setupClusterManager()

        val listObserver = Observer { events: List<Event> ->
            mClusterManager.addItems(events)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(franceLatLng, 5.0f))
        }

        model.eventsList.observe(this, listObserver)

    }

    /**
     * Setup the Cluster Manager which will render the markers.
     * @see <a href="https://developers.google.com/maps/documentation/android-sdk/utility/marker-clustering">Cluster Manager Documentation</a>
     */
    private fun setupClusterManager() {
        mClusterManager = ClusterManager(context, mMap)
        mMap.setOnCameraIdleListener(mClusterManager)

        mClusterManager.setOnClusterItemClickListener { event: Event? ->
            if (event != null) {
                val intent = Intent(activity, EventDetailsActivity::class.java)
                intent.putExtra(EXTRA_EVENT_ID, event.id)
                startActivity(intent)
            }

            true
        }

        mMap.setOnMarkerClickListener(mClusterManager)
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }
}
