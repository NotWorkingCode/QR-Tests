package not.working.code.qrtests.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_test.view.*
import not.working.code.qrtests.R
import not.working.code.qrtests.utils.enums.ClickType

class TestsAdapter(val context: Context, val clickCallback: (position: Int, clickType: ClickType) -> Unit): RecyclerView.Adapter<TestsAdapter.TestsViewHolder>() {

    private val tests = ArrayList<String>()

    class TestsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.list_item_test_tv_title
        val deleteBtn = itemView.list_item_test_ibtn_delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_test, parent, false)
        return TestsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tests.size
    }

    override fun onBindViewHolder(holder: TestsViewHolder, position: Int) {
        holder.apply {
            title.text = tests[position]
            itemView.setOnClickListener {
                clickCallback(position, ClickType.OPEN_TEST)
            }
            deleteBtn.setOnClickListener {
                clickCallback(position, ClickType.DELETE_TEST)
            }
        }
    }

    fun updateAll(tests: ArrayList<String>) {
        this.tests.apply {
            clear()
            addAll(tests)
        }
        notifyDataSetChanged()
    }

    fun addItem(test: String) {
        tests.add(test)
        notifyDataSetChanged()
    }
}