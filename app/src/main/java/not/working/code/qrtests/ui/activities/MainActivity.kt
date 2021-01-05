package not.working.code.qrtests.ui.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.adapters.TestsAdapter
import not.working.code.qrtests.ui.presenters.MainPresenter
import not.working.code.qrtests.ui.views.MainView
import kotlin.collections.ArrayList
import not.working.code.qrtests.utils.*

class MainActivity : MvpAppCompatActivity(), MainView{

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private lateinit var adapter: TestsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = TestsAdapter(context = this)
        e_recycler_tests.layoutManager = LinearLayoutManager(this)
        e_recycler_tests.adapter = adapter
    }

    override fun updateTestAdapter(tests: ArrayList<String>) {
        adapter.updateAll(tests = tests)
    }

    override fun addTest(test: String) {
        adapter.addItem(test = test)
    }

    override fun showProgress() {
        d_load_tests_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        d_load_tests_progress.visibility = View.GONE
    }
}