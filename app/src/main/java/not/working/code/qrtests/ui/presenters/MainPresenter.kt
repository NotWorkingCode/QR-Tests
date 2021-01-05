package not.working.code.qrtests.ui.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import not.working.code.qrtests.ui.views.MainView

class MainPresenter: MvpPresenter<MainView>() {

    private var tests = ArrayList<String>()

    override fun onFirstViewAttach() {
        if (tests.isEmpty()) {
            viewState.showProgress()
            loadTests()
        } else {
            viewState.hideProgress()
            viewState.updateTestAdapter(tests)
        }
    }

    private fun loadTests() {
        //TODO (add load tests)
        viewState.hideProgress()
        viewState.updateTestAdapter(tests)
    }

}