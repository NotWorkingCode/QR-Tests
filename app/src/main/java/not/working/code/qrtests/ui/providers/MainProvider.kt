package not.working.code.qrtests.ui.providers

import android.content.Context
import android.os.Environment
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import not.working.code.qrtests.ui.presenters.MainPresenter
import java.io.File
import not.working.code.qrtests.utils.config.*
import java.lang.Exception

class MainProvider(val presenter: MainPresenter, val context: Context) {

    private val TESTS_PATH =  File(context.getExternalFilesDir(null), DEFAULT_TESTS_PATH)

    init {
        createFolder()
    }

    fun loadAllTests(): Observable<String> {
        return Observable.create<String> {emitter ->
            val files: Array<File>? = TESTS_PATH.listFiles()
            if (files == null) emitter.onError(Exception("Empty directory"))
            files?.forEach {
                emitter.onNext(it.nameWithoutExtension)
            }
            emitter.onComplete()
        }
    }

    private fun createFolder(){

        if (!TESTS_PATH.isDirectory) {
            val t = TESTS_PATH.mkdir()
        }
    }
}