package not.working.code.qrtests.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun updateTestAdapter(tests: ArrayList<String>)
    fun addTest(test: String)
    fun showProgress()
    fun hideProgress()
}