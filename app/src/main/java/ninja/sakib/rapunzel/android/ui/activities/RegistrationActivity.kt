package ninja.sakib.rapunzel.android.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.models.error.ApiError
import ninja.sakib.rapunzel.android.models.error.GRPCError
import ninja.sakib.rapunzel.android.models.error.NetworkError
import ninja.sakib.rapunzel.android.presenters.RegistrationPresenter
import ninja.sakib.rapunzel.android.proto.ReqRegistration
import ninja.sakib.rapunzel.android.proto.ResRegistration
import ninja.sakib.rapunzel.android.ui.views.RegistrationView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class RegistrationActivity : BaseActivity<RegistrationView, RegistrationPresenter>(), RegistrationView {
    private lateinit var regFirstName: EditText
    private lateinit var regLastName: EditText
    private lateinit var regUsername: EditText
    private lateinit var regEmailAddress: EditText
    private lateinit var regPassword: EditText
    private lateinit var regSignUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        enableGoBackBtn()
        setActionBarTitle("Registration")
        bindUI()
    }

    private fun bindUI() {
        regFirstName = find(R.id.regFirstName)
        regLastName = find(R.id.regLastName)
        regUsername = find(R.id.regUsername)
        regEmailAddress = find(R.id.regEmailAddress)
        regPassword = find(R.id.regPassword)
        regSignUpBtn = find(R.id.regSignUpBtn)
        regSignUpBtn.onClick {
            onSignUpRequest()
        }
    }

    override fun createPresenter(): RegistrationPresenter {
        return RegistrationPresenter()
    }

    private fun isFieldsValid(): Boolean {
        if (regFirstName.text.toString().trim().isEmpty()) {
            toast("First name must not be empty")
            return false
        }
        if (regLastName.text.toString().trim().isEmpty()) {
            toast("Last name must not be empty")
            return false
        }
        if (regUsername.text.toString().trim().isEmpty()) {
            toast("Username must not be empty")
            return false
        }
        if (regEmailAddress.text.toString().trim().isEmpty()) {
            return false
        }
        if (regPassword.text.toString().trim().isEmpty()) {
            return false
        }
        return true
    }

    private fun onSignUpRequest() {
        if (isFieldsValid()) {
            showLoadingDialog()

            val req = ReqRegistration.newBuilder()
                    .setName("${regFirstName.text.toString().trim()} ${regLastName.text.toString().trim()}")
                    .setUsername(regUsername.text.toString().trim())
                    .setEmail(regEmailAddress.text.toString().trim())
                    .setPassword(regPassword.text.toString().trim())
                    .build()

            presenter.requestRegistration(req)
        }
    }

    override fun onRegistrationSuccess(res: ResRegistration) {
        runOnUiThread {
            hideLoadingDialog()
            toast("Registration successful")
            onBackPressed()
        }
    }

    override fun onRegistrationFailure(err: ApiError) {
        runOnUiThread {
            hideLoadingDialog()
            if (err is NetworkError) {
                toast("Couldn't connect to service")
                return@runOnUiThread
            }
            if (err is GRPCError) {
                toast(err.details)
                return@runOnUiThread
            }
        }
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
