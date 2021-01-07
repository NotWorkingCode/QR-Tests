package not.working.code.qrtests.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import not.working.code.qrtests.utils.data.Question
import not.working.code.qrtests.utils.enums.ChangeQuestion

@StateStrategyType(AddToEndSingleStrategy::class)
interface CreateTestView: MvpView {
    fun changeQuestion(mode: ChangeQuestion, question: Question)
    fun addQuestion()
    fun updateInformer(currentQuestion: Int, maxQuestion: Int)
    fun showError(message: Int)
}