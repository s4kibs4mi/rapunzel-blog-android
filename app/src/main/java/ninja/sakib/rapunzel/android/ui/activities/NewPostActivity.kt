package ninja.sakib.rapunzel.android.ui.activities

import android.os.Bundle
import android.view.MenuItem
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.presenters.NewPostPresenter
import ninja.sakib.rapunzel.android.ui.views.NewPostView

class NewPostActivity : BaseActivity<NewPostView, NewPostPresenter>(), NewPostView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        enableGoBackBtn()
        setActionBarTitle("Write New Post")
    }

    override fun createPresenter(): NewPostPresenter {
        return NewPostPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return false
            }
        }
        return true
    }
}
