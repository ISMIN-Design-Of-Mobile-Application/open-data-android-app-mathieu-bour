package fr.mathieubour.fetedelascience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import fr.mathieubour.fetedelascience.R

/**
 * Static fragment which holds my signature and all the licenses of the used libraries.
 */
class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Open the License activity
        activity!!.findViewById<Button>(R.id.licenses_button).setOnClickListener {
            startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    }
}