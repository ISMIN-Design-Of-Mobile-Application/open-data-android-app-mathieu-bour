package fr.mathieubour.fetedelascience

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import fr.mathieubour.fetedelascience.models.MainViewModel
import fr.mathieubour.fetedelascience.ui.main.TabsAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var reloadEventsButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this)[MainViewModel::class.java]

        // Tabs
        val sectionsPagerAdapter = TabsAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Reload and progress bar
        progressBar = findViewById(R.id.progressBar)
        reloadEventsButton = findViewById(R.id.fab)
        reloadEventsButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            progressBar.isIndeterminate = true
            model.reloadEvents()
        }

        model.getEventsList().observe(this, Observer {
            progressBar.isIndeterminate = false
            progressBar.visibility = View.GONE
        })

        val permission: Array<String> = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        requestPermissions(permission, 1)
    }
}