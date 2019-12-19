package fr.mathieubour.fetedelascience.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fr.mathieubour.fetedelascience.R


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private val tabsNames = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
    )

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> ListFragment()
            1 -> MapFragment()
            2 -> AboutFragment()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(tabsNames[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return tabsNames.size
    }
}