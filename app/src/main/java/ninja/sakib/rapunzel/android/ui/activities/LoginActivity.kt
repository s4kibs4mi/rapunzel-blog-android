package ninja.sakib.rapunzel.android.ui.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.models.error.ApiError
import ninja.sakib.rapunzel.android.models.error.GRPCError
import ninja.sakib.rapunzel.android.models.error.NetworkError
import ninja.sakib.rapunzel.android.presenters.LoginPresenter
import ninja.sakib.rapunzel.android.proto.ReqLogin
import ninja.sakib.rapunzel.android.proto.ResLogin
import ninja.sakib.rapunzel.android.ui.views.LoginView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity<LoginView, LoginPresenter>(), LoginView {
    private lateinit var usernameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameET = find(R.id.loginUsername)
        passwordET = find(R.id.loginPassword)
        loginBtn = find(R.id.loginBtn)
        loginBtn.onClick {
            onLoginRequest()
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

    override fun onLoginSuccess(res: ResLogin) {
        runOnUiThread {
            hideLoadingDialog()
            toast("Login successful")
        }
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
