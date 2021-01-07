package not.working.code.qrtests.ui.animations

import android.view.View


class FABAnimation {
    companion object {
        fun rotateFAB(view: View, rotate: Boolean) {
            view.animate()
                    .setDuration(200)
                    .rotation(if (rotate) 135f else 0f)
        }
        fun showIn(view: View) {
            view.apply {
                visibility = View.VISIBLE
                alpha = 0f
                translationY = view.height.toFloat()
                animate()
                        .setDuration(200)
                        .translationY(0f)
                        .alpha(1f)
                        .start()
            }
        }
        fun showOut(view: View) {
            view.apply {
                visibility = View.VISIBLE
                alpha = 1f
                translationY = 0f
                animate()
                        .setDuration(200)
                        .translationY(view.height.toFloat())
                        .alpha(0f)
                        .start()
            }
        }
        fun init(view: View) {
            view.apply {
                visibility = View.GONE
                translationY = view.height.toFloat()
                alpha = 0f
            }
        }
    }
}