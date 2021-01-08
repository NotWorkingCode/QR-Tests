package not.working.code.qrtests.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_test.*
import not.working.code.qrtests.R
import not.working.code.qrtests.ui.fragments.CreateTestFragment

class CreateTestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)
        supportActionBar?.elevation = 0f
        supportFragmentManager.beginTransaction().replace(R.id.activity_create_test_root, CreateTestFragment(), null).commit()
    }
}