package not.working.code.qrtests.ui.animations

import android.view.View

class ChangeQuestionAnimation {
    companion object {
        fun showPrevious(view: View, hideCallback: () -> Unit) {
            view.apply {
                alpha = 1f
                translationY = 0f
                animate()
                        .setDuration(200)
                        .alpha(0f)
                        .translationY(-100f)
                        .withEndAction {
                            hideCallback()
                            alpha = 0f
                            translationY = 100f
                            animate()
                                    .setDuration(200)
                                    .alpha(1f)
                                    .translationY(0f)
                        }
            }
        }
        fun showNext(view: View, hideCallback: () -> Unit) {
            view.apply {
                alpha = 1f
                translationY = 0f
                animate()
                        .setDuration(200)
                        .alpha(0f)
                        .translationY(100f)
                        .withEndAction {
                            hideCallback()
                            alpha = 0f
                            translationY = -100f
                            animate()
                                    .setDuration(200)
                                    .alpha(1f)
                                    .translationY(0f)
                        }
            }
        }
    }
}