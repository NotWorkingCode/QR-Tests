package not.working.code.qrtests.ui.presenters

import android.util.Log
import moxy.InjectViewState
import moxy.MvpPresenter
import not.working.code.qrtests.ui.views.MainView

class MainPresenter: MvpPresenter<MainView>() {

    private var tests = ArrayList<String>()

    override fun onFirstViewAttach() {
        viewState.checkPermission()
    }

    fun permissionGrated() {
        viewState.hideRequestPermissionButton()
        if (tests.isEmpty()) {
            viewState.showProgress()
            loadTests()
        } else {
            viewState.hideProgress()
            viewState.updateTestAdapter(tests)
        }
    }

    fun permissionDenied() {
        viewState.showRequestPermissionButton()
    }

    private fun loadTests() {
        tests.add("Test")
        viewState.hideProgress()
        viewState.updateTestAdapter(tests)
    }
}