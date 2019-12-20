package fr.mathieubour.fetedelascience

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.mathieubour.fetedelascience.data.EventsDatabase
import fr.mathieubour.fetedelascience.models.MainViewModel
import fr.mathieubour.fetedelascience.ui.main.TabsAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity of the application which is composed of three tabs:
 * 1. The events list (ListFragment)
 * 2. The map (MapFragment)
 * 3. The about page (AboutFragment)
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this)[MainViewModel::class.java]
        model.setEventDao(EventsDatabase.getInstance(this).eventDao())

        // Tabs
        val sectionsPagerAdapter = TabsAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        // Reload and display progress bar
        refresh.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            progress_bar.isIndeterminate = true
            model.reloadEvents()
        }

        // When reloaded, hide the progress bar
        model.eventsList.observe(this, Observer {
            if (it.isNotEmpty()) {
                progress_bar.isIndeterminate = false
                progress_bar.visibility = View.GONE
            }
        })
    }
}