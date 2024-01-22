package uiHelpher

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import uiHelpher.OnSnackBar
import com.google.android.material.snackbar.Snackbar
import com.swg.task.R
import android.widget.TextView
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class OnSnackBar(mActivity: Activity, view: View?, msg: String) {

    private val TAG = OnSnackBar::class.java.simpleName

    init {
        try {

            val snackBar = Snackbar.make(view!!, "" + msg, Snackbar.LENGTH_LONG)
                .setAction("") { }
            snackBar.setActionTextColor(Color.GREEN)
            snackBar.setBackgroundTint(mActivity.resources.getColor(R.color.white1))
            val sbView = snackBar.view
            val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setBackgroundColor(mActivity.resources.getColor(R.color.white1))
            textView.setTextColor(Color.BLACK)
            snackBar.show()

        } catch (e: NullPointerException) {
            Log.e(TAG, "NullPointerException " + e.message)
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "NullPointerException " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception " + e.message)
        }
    }
}