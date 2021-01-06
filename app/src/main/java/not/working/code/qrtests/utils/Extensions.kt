package not.working.code.qrtests.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}
fun Context.showToast(message: Int) {
    Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
}
