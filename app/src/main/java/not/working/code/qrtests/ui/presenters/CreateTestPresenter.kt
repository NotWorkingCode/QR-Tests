package not.working.code.qrtests.ui.presenters

import moxy.MvpPresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.views.CreateTestView
import not.working.code.qrtests.utils.data.Question
import not.working.code.qrtests.utils.enums.ChangeQuestion

class CreateTestPresenter: MvpPresenter<CreateTestView>() {

    private var maxQuestion = 1
    private var currentQuestion = 1

    private val questions = HashMap<Int, Question>()

    fun changeQuestion(mode: ChangeQuestion, question: Question) {
        if (isNotEmptyQuestion(question = question)) {
            if (isNotMaxLengthQuestion(question = question)) {
                questions.put(currentQuestion, question)
                when(mode) {
                    ChangeQuestion.NEXT -> {
                        if (currentQuestion < maxQuestion) {
                            currentQuestion += 1
                            viewState.changeQuestion(mode = ChangeQuestion.NEXT, question = questions.get(currentQuestion)!!)
                        } else viewState.showError(R.string.error_test_create_last_question)
                    }
                    ChangeQuestion.PREVIOUS -> {
                        if (currentQuestion != 1) {
                            currentQuestion -= 1
                            viewState.changeQuestion(mode = ChangeQuestion.PREVIOUS, question = questions.get(currentQuestion)!!)
                        } else viewState.showError(R.string.error_test_create_first_question)
                    }
                }
                viewState.updateInformer(currentQuestion = currentQuestion, maxQuestion = maxQuestion)
            } else viewState.showError(R.string.error_test_create_max)
        } else viewState.showError(R.string.error_test_create_empty)
    }

    fun addQuestion(question: Question) {
        if (isNotEmptyQuestion(question = question)) {
            if (isNotMaxLengthQuestion(question = question)) {
                if (currentQuestion < 10) {
                    questions.put(currentQuestion, question)
                    maxQuestion += 1
                    currentQuestion = maxQuestion
                    viewState.updateInformer(currentQuestion = currentQuestion, maxQuestion = maxQuestion)
                    viewState.addQuestion()
                } else viewState.showError(R.string.error_test_create_max_question)
            } else viewState.showError(R.string.error_test_create_max)
        } else viewState.showError(R.string.error_test_create_empty)
    }

    private fun isNotEmptyQuestion(question: Question): Boolean {
        if (question.question.isEmpty()) return false
        if (question.answerOne.isEmpty()) return false
        if (question.answerTwo.isEmpty()) return false
        if (question.answerThree.isEmpty()) return false
        if (question.answerFour.isEmpty()) return false
        return true
    }

    private fun isNotMaxLengthQuestion(question: Question): Boolean {
        if (question.question.length > 80) return false
        if (question.answerOne.length > 30) return false
        if (question.answerTwo.length > 30) return false
        if (question.answerThree.length > 30) return false
        if (question.answerFour.length > 30) return false
        return true
    }
}