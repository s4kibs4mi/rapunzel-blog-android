package ninja.sakib.rapunzel.android.presenters

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import ninja.sakib.rapunzel.android.models.error.GRPCError
import ninja.sakib.rapunzel.android.models.error.NetworkError
import ninja.sakib.rapunzel.android.proto.ReqLogin
import ninja.sakib.rapunzel.android.services.getRapunzelBlogService
import ninja.sakib.rapunzel.android.ui.views.LoginView
import org.jetbrains.anko.doAsync
import java.lang.Exception

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class LoginPresenter : MvpBasePresenter<LoginView>() {
    fun requestLogin(req: ReqLogin) {
        doAsync {
            try {
                val res = getRapunzelBlogService()!!.login(req)
                if (res != null && res.errorsCount == 0 && isViewAttached) {
                    view!!.onLoginSuccess(res)
                    return@doAsync
                }
                if (res != null && res.errorsCount > 0 && isViewAttached) {
                    for (err in res.errorsList) {
                        view!!.onLoginFailure(GRPCError(err.details))
                        return@doAsync
                    }
                }
                if (isViewAttached) {
                    view!!.onLoginFailure(NetworkError())
                }
            } catch (e: Exception) {
                if (isViewAttached) {
                    view!!.onLoginFailure(NetworkError())
                }
            }
        }
    }
}
