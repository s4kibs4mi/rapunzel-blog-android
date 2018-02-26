package ninja.sakib.rapunzel.android.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.models.error.ApiError
import ninja.sakib.rapunzel.android.models.error.GRPCError
import ninja.sakib.rapunzel.android.models.error.NetworkError
import ninja.sakib.rapunzel.android.presenters.LoginPresenter
import ninja.sakib.rapunzel.android.proto.ReqLogin
import ninja.sakib.rapunzel.android.proto.ResLogin
import ninja.sakib.rapunzel.android.services.saveSession
import ninja.sakib.rapunzel.android.ui.views.LoginView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerNow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        hideTitleBar()
        bindUI()
    }

    private fun bindUI() {
        usernameET = find(R.id.loginUsername)
        passwordET = find(R.id.loginPassword)
        loginBtn = find(R.id.loginBtn)
        registerNow = find(R.id.loginRegisterNow)
        loginBtn.onClick {
            onLoginRequest()
        }
        registerNow.onClick {
            onRegisterNowRequest()
        }
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    private fun isFieldsValid(): Boolean {
        if (usernameET.text.toString().trim().isEmpty()) {
            toast("Username must not be empty")
            return false
        }
        if (passwordET.text.toString().trim().isEmpty()) {
            toast("Password must not be empty")
            return false
        }
        return true
    }

    private fun onLoginRequest() {
        if (isFieldsValid()) {
            showLoadingDialog()
            val req = ReqLogin
                    .newBuilder()
                    .setUsername(usernameET.text.toString())
                    .setPassword(passwordET.text.toString())
                    .build()
            presenter.requestLogin(req)
        }
    }

    private fun onRegisterNowRequest() {
        startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        finish()
    }

    override fun onLoginSuccess(res: ResLogin) {
        if (saveSession(res.session).not()) {
            return
        }

        runOnUiThread {
            hideLoadingDialog()
            toast("Login successful")
        }
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    override fun onLoginFailure(err: ApiError) {
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
}
