package not.working.code.qrtests.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create_test.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.animations.ChangeQuestionAnimation
import not.working.code.qrtests.ui.presenters.CreateTestPresenter
import not.working.code.qrtests.ui.views.CreateTestView
import not.working.code.qrtests.utils.data.Question
import not.working.code.qrtests.utils.enums.ChangeQuestion
import not.working.code.qrtests.utils.showSnackbar

class CreateTestFragment: MvpAppCompatFragment(), CreateTestView {

    @InjectPresenter
    lateinit var presenter: CreateTestPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.title = getString(R.string.create_test_title_step_one)

        fragment_create_test_btn_next.setOnClickListener {
            presenter.changeQuestion(mode = ChangeQuestion.NEXT, question = getQuestion())
        }

        fragment_create_test_btn_previous.setOnClickListener {
            presenter.changeQuestion(mode = ChangeQuestion.PREVIOUS, question = getQuestion())
        }

        fragment_create_test_btn_add.setOnClickListener {
            presenter.addQuestion(question = getQuestion())
        }
    }

    override fun changeQuestion(mode: ChangeQuestion, question: Question) {
        when(mode) {
            ChangeQuestion.NEXT -> {
                ChangeQuestionAnimation.showNext(fragment_create_test_root) {setQuestion(question)}
            }
            ChangeQuestion.PREVIOUS -> {
                ChangeQuestionAnimation.showPrevious(fragment_create_test_root) {setQuestion(question)}
            }
        }
    }

    override fun addQuestion() {
        ChangeQuestionAnimation.showNext(fragment_create_test_root) {clearEditText()}
    }

    override fun updateInformer(currentQuestion: Int, maxQuestion: Int) {
        fragment_create_test_informer_tv_current_question.text = currentQuestion.toString()
        fragment_create_test_informer_tv_max_question.text = maxQuestion.toString()
    }

    override fun showError(message: Int) {
        activity?.showSnackbar(view = fragment_create_test_ll_question_manager, message = message)
    }

    private fun clearEditText() {
        arrayOf(fragment_create_test_et_question, fragment_create_test_et_answer_one, fragment_create_test_et_answer_two, fragment_create_test_et_answer_three, fragment_create_test_et_answer_four).forEach {
            it.text = null
        }
    }

    private fun getQuestion(): Question {
        return Question(question = fragment_create_test_et_question.text.toString(),
                answerOne = fragment_create_test_et_answer_one.text.toString(),
                answerTwo = fragment_create_test_et_answer_two.text.toString(),
                answerFour = fragment_create_test_et_answer_three.text.toString(),
                answerThree = fragment_create_test_et_answer_four.text.toString(),
                answerCorrect = null)
    }

    private fun setQuestion(question: Question) {
        fragment_create_test_et_answer_one.setText(question.answerOne)
        fragment_create_test_et_answer_two.setText(question.answerTwo)
        fragment_create_test_et_answer_three.setText(question.answerThree)
        fragment_create_test_et_answer_four.setText(question.answerFour)
        fragment_create_test_et_question.setText(question.question)
    }
}