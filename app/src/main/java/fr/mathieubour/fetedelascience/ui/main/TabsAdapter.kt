package fr.mathieubour.fetedelascience.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fr.mathieubour.fetedelascience.R

/**
 * Handle the TabLayout of the MainActivity
 */
class TabsAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabsNames = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
    )

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ListFragment()
        1 -> MapFragment()
        else -> AboutFragment()
    }


    override fun getPageTitle(position: Int) = context.resources.getString(tabsNames[position])

    override fun getCount() = tabsNames.size
}