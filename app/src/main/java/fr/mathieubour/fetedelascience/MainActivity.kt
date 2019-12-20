package fr.mathieubour.fetedelascience

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import fr.mathieubour.fetedelascience.data.EventsDatabase
import fr.mathieubour.fetedelascience.models.MainViewModel
import fr.mathieubour.fetedelascience.ui.main.TabsAdapter

/**
 * Main activity of the application which is composed of three tabs:
 * 1. The events list (ListFragment)
 * 2. The map (MapFragment)
 * 3. The about page (AboutFragment)
 */
class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this)[MainViewModel::class.java]
        model.setEventDao(EventsDatabase.getInstance(this).eventDao())

        // Tabs
        val sectionsPagerAdapter = TabsAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Reload and progress bar
        progressBar = findViewById(R.id.progressBar)

        findViewById<ImageView>(R.id.refresh).setOnClickListener {
            progressBar.visibility = View.VISIBLE
            progressBar.isIndeterminate = true
            model.reloadEvents()
        }

        model.eventsList.observe(this, Observer {
            if (it.isNotEmpty()) {
                progressBar.isIndeterminate = false
                progressBar.visibility = View.GONE
            }
        })
    }
}