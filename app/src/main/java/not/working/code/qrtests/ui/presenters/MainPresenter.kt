package not.working.code.qrtests.ui.presenters

import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.providers.MainProvider
import not.working.code.qrtests.ui.views.MainView

class MainPresenter(val context: Context): MvpPresenter<MainView>() {

    private var tests = ArrayList<String>()
    private val provider = MainProvider(presenter = this, context = context)

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

    fun openTest(position: Int) {
        Log.e("TEST_CLICK", "I open project -> ${tests[position]}")
    }

    fun deleteTest(position: Int) {
        Log.e("TEST_CLICK", "I delete project -> ${tests[position]}")
    }

    private fun loadTests() {
        val dispose = provider.loadAllTests()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    tests.add(it)
                }, {
                    viewState.hideProgress()
                    viewState.showError(R.string.empty_tests_folder)
                }, {
                    viewState.hideProgress()
                    viewState.updateTestAdapter(tests)
                })
    }
}