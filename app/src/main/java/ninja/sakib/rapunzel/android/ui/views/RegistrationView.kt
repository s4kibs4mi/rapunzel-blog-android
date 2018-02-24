package ninja.sakib.rapunzel.android.ui.views

import com.hannesdorfmann.mosby.mvp.MvpView
import ninja.sakib.rapunzel.android.models.error.ApiError
import ninja.sakib.rapunzel.android.proto.ResRegistration

/**
 * := Coded with love by Sakib Sami on 24/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

interface RegistrationView : MvpView {
    fun onRegistrationSuccess(res: ResRegistration)
    fun onRegistrationFailure(err: ApiError)
}
