package not.working.code.qrtests.ui.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_test.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.presenters.CreateTestPresenter
import not.working.code.qrtests.ui.views.CreateTestView
import not.working.code.qrtests.utils.data.Question
import not.working.code.qrtests.utils.enums.ChangeQuestion
import not.working.code.qrtests.utils.showSnackbar

class CreateTestActivity: MvpAppCompatActivity(), CreateTestView {

    @InjectPresenter
    lateinit var presenter: CreateTestPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)

        title = getString(R.string.create_test_title_step_one)
        supportActionBar?.elevation = 0f

        activity_create_test_btn_next.setOnClickListener {
            presenter.changeQuestion(mode = ChangeQuestion.NEXT, question = getQuestion())
        }

        activity_create_test_btn_previous.setOnClickListener {
            presenter.changeQuestion(mode = ChangeQuestion.PREVIOUS, question = getQuestion())
        }

        activity_create_test_btn_add.setOnClickListener {
            presenter.addQuestion(question = getQuestion())
        }
    }

    override fun changeQuestion(mode: ChangeQuestion, question: Question) {
        setQuestion(question = question)
        when(mode) {
            ChangeQuestion.NEXT -> {}
            ChangeQuestion.PREVIOUS -> {}
        }
    }

    override fun addQuestion() {
        clearEditText()
    }

    override fun updateInformer(currentQuestion: Int, maxQuestion: Int) {
        activity_create_test_informer_tv_current_question.text = currentQuestion.toString()
        activity_create_test_informer_tv_max_question.text = maxQuestion.toString()
    }

    override fun showError(message: Int) {
        showSnackbar(view = activity_create_test_ll_question_manager, message = message)
    }

    private fun clearEditText() {
        arrayOf(activity_create_test_et_question, activity_create_test_et_answer_one, activity_create_test_et_answer_two, activity_create_test_et_answer_three, activity_create_test_et_answer_four).forEach {
            it.text = null
        }
    }

    private fun getQuestion(): Question {
        return Question(question = activity_create_test_et_question.text.toString(),
                answerOne = activity_create_test_et_answer_one.text.toString(),
                answerTwo = activity_create_test_et_answer_two.text.toString(),
                answerFour = activity_create_test_et_answer_three.text.toString(),
                answerThree = activity_create_test_et_answer_four.text.toString(),
                answerCorrect = null)
    }

    private fun setQuestion(question: Question) {
        activity_create_test_et_answer_one.setText(question.answerOne)
        activity_create_test_et_answer_two.setText(question.answerTwo)
        activity_create_test_et_answer_three.setText(question.answerThree)
        activity_create_test_et_answer_four.setText(question.answerFour)
        activity_create_test_et_question.setText(question.question)
    }
}