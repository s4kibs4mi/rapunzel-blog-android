package ninja.sakib.rapunzel.android.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.presenters.RegistrationPresenter
import ninja.sakib.rapunzel.android.ui.views.RegistrationView

class RegistrationActivity : BaseActivity<RegistrationView, RegistrationPresenter>(), RegistrationView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        enableGoBackBtn()
        setActionBarTitle("Registration")
    }

    override fun createPresenter(): RegistrationPresenter {
        return RegistrationPresenter()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }
}
