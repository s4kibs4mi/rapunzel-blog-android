package ninja.sakib.rapunzel.android.ui.views

import com.hannesdorfmann.mosby.mvp.MvpView
import ninja.sakib.rapunzel.android.models.error.ApiError
import ninja.sakib.rapunzel.android.proto.ResLogin

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

interface LoginView : MvpView {
    fun onLoginSuccess(res: ResLogin)
    fun onLoginFailure(err: ApiError)
}
