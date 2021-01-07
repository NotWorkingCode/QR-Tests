package not.working.code.qrtests.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.adapters.TestsAdapter
import not.working.code.qrtests.ui.animations.FABAnimation
import not.working.code.qrtests.ui.presenters.MainPresenter
import not.working.code.qrtests.ui.views.MainView
import kotlin.collections.ArrayList
import not.working.code.qrtests.utils.*
import not.working.code.qrtests.utils.enums.ClickType

class MainActivity : MvpAppCompatActivity(), MainView{

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun provide(): MainPresenter{
        return MainPresenter(context = applicationContext)
    }

    private lateinit var adapter: TestsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = TestsAdapter(context = this) {position, clickType ->
            when(clickType) {
                ClickType.OPEN_TEST -> presenter.openTest(position = position)
                ClickType.DELETE_TEST -> presenter.deleteTest(position = position)
            }
        }
        activity_main_rv_tests.layoutManager = LinearLayoutManager(this)
        activity_main_rv_tests.adapter = adapter

        activity_main_btn_request_permission.setOnClickListener {
            checkPermission()
        }

        FABAnimation.init(activity_main_fab_create_test)
        FABAnimation.init(activity_main_fab_qr_scanning)

        activity_main_fab_main.setOnClickListener {
            presenter.clickFAB()
        }

        activity_main_fab_create_test.setOnClickListener {
            startActivity(Intent(this, CreateTestActivity::class.java))
        }
    }

    override fun updateTestAdapter(tests: ArrayList<String>) {
        adapter.updateAll(tests = tests)
    }

    override fun addTest(test: String) {
        adapter.addItem(test = test)
    }

    override fun showProgress() {
        activity_main_pb_load_tests.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        activity_main_pb_load_tests.visibility = View.GONE
    }

    override fun showRequestPermissionButton() {
        activity_main_btn_request_permission.visibility = View.VISIBLE
    }

    override fun hideRequestPermissionButton() {
        activity_main_btn_request_permission.visibility = View.GONE
    }

    override fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
            } else {
                presenter.permissionGrated()
            }
        }
    }

    override fun showError(message: Int) {
        showToast(message = message)
    }

    override fun hideEmptyTestList() {
        activity_main_tv_no_tests.visibility = View.GONE
    }

    override fun showFAB() {
        FABAnimation.rotateFAB(view = activity_main_fab_main, rotate =  true)
        FABAnimation.showIn(view = activity_main_fab_create_test)
        FABAnimation.showIn(view = activity_main_fab_qr_scanning)
    }

    override fun hideFAB() {
        FABAnimation.rotateFAB(view = activity_main_fab_main, rotate =  false)
        FABAnimation.showOut(view = activity_main_fab_create_test)
        FABAnimation.showOut(view = activity_main_fab_qr_scanning)
    }

    override fun showEmptyTestsList() {
        activity_main_tv_no_tests.visibility = View.VISIBLE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            101 -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.permissionGrated()
                } else {
                    presenter.permissionDenied()
                }
            }
        }
    }
}
