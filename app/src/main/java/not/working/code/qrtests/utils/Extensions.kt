package not.working.code.qrtests.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}
fun Context.showToast(message: Int) {
    Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
}

fun Context.showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}
fun Context.showSnackbar(view: View, message: Int) {
    Snackbar.make(view, getString(message), Snackbar.LENGTH_SHORT).show()
}
