package not.working.code.qrtests.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
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

        d_request_permission_button.setOnClickListener {
            checkPermission()
        }
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

    override fun showRequestPermissionButton() {
        d_request_permission_button.visibility = View.VISIBLE
    }

    override fun hideRequestPermissionButton() {
        d_request_permission_button.visibility = View.GONE
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            101 -> {
                Log.e("TEST_PERMISSION", grantResults.size.toString())
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.permissionGrated()
                } else {
                    presenter.permissionDenied()
                }
            }
        }
    }
}
