package ninja.sakib.rapunzel.android.ui.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.presenters.NewPostPresenter
import ninja.sakib.rapunzel.android.services.getLastTempPost
import ninja.sakib.rapunzel.android.services.writeLastTempPost
import ninja.sakib.rapunzel.android.ui.views.NewPostView
import org.jetbrains.anko.find
import ru.noties.markwon.Markwon
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.widget.Scroller

class NewPostActivity : BaseActivity<NewPostView, NewPostPresenter>(), NewPostView, TextWatcher {
    private lateinit var editor: EditText
    private lateinit var liveView: TextView

    private var lastChange = 0
    private var countableChange = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        enableGoBackBtn()
        setActionBarTitle("New Post")

        bindUI()
    }

    private fun bindUI() {
        editor = find(R.id.newPostEditor)
        liveView = find(R.id.newPostLiveView)
        editor.setScroller(Scroller(applicationContext))
        editor.maxLines = 100
        editor.isVerticalScrollBarEnabled = true
        editor.movementMethod = ScrollingMovementMethod()
        editor.addTextChangedListener(this)
        editor.setText(getLastTempPost())
        renderLiveView()
    }

    override fun createPresenter(): NewPostPresenter {
        return NewPostPresenter()
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (Math.abs(p1 - lastChange) >= countableChange) {
            renderLiveView()
            writeLastTempPost(editor.text.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_post_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return false
            }
            R.id.new_post_clear -> {
                writeLastTempPost("")
                editor.setText("")
                renderLiveView()
                return false
            }
            R.id.new_post_save -> {
                onPostSave()
                return false
            }
        }
        return true
    }

    private fun renderLiveView() {
        Markwon.setMarkdown(liveView, editor.text.toString())
    }

    private fun onPostSave() {

    }

    private fun requestPostUpdate() {

    }

    private fun requestPostCreate() {

    }
}
