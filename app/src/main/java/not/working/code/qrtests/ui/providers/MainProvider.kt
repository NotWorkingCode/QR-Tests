package not.working.code.qrtests.ui.providers

import android.os.Environment
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import not.working.code.qrtests.ui.presenters.MainPresenter
import java.io.File
import not.working.code.qrtests.utils.config.*

class MainProvider(val presenter: MainPresenter) {

    init {
        createFolder()
    }

    fun loadAllTests() {
        Observable.create<String> {

        }
    }

    private fun createFolder(){
        val file = File(Environment.getExternalStorageDirectory().path + "/" + DEFAULT_TESTS_PATH)
        if (!file.isDirectory) {
            file.mkdir()
        }
        Log.e("TEST_FILE", file.path)
    }
}