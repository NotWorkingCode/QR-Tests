package not.working.code.qrtests.ui.providers

import android.content.Context
import io.reactivex.rxjava3.core.Observable
import java.io.File
import not.working.code.qrtests.utils.config.*
import not.working.code.qrtests.utils.exceptions.EmptyTestsDirectoryException

class MainProvider(val context: Context) {

    private val TESTS_PATH =  File(context.getExternalFilesDir(null), DEFAULT_TESTS_PATH)

    init {
        createFolder()
    }

    fun loadAllTests(): Observable<String> {
        return Observable.create<String> {emitter ->
            val files: Array<File>? = TESTS_PATH.listFiles()
            if (files?.size == 0) emitter.onError(EmptyTestsDirectoryException())
            files?.forEach {
                emitter.onNext(it.nameWithoutExtension)
            }
            emitter.onComplete()
        }
    }

    private fun createFolder(){
        if (!TESTS_PATH.isDirectory) {
            TESTS_PATH.mkdir()
        }
    }
}