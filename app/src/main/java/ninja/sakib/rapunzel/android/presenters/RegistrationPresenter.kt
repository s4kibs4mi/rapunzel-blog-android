package ninja.sakib.rapunzel.android.presenters

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import ninja.sakib.rapunzel.android.models.error.GRPCError
import ninja.sakib.rapunzel.android.models.error.NetworkError
import ninja.sakib.rapunzel.android.proto.ReqRegistration
import ninja.sakib.rapunzel.android.services.getRapunzelBlogService
import ninja.sakib.rapunzel.android.ui.views.RegistrationView
import ninja.sakib.rapunzel.android.utils.httpErrorUnprocessableEntity
import org.jetbrains.anko.doAsync

/**
 * := Coded with love by Sakib Sami on 24/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class RegistrationPresenter : MvpBasePresenter<RegistrationView>() {

    fun requestRegistration(req: ReqRegistration) {
        doAsync {
            try {
                val res = getRapunzelBlogService()!!.register(req)
                if (res != null && res.errorsCount == 0 && isViewAttached) {
                    view!!.onRegistrationSuccess(res)
                    return@doAsync
                }
                if (res != null && res.errorsCount > 0 && isViewAttached) {
                    val err = res.errorsList[0]
                    if (err.code == httpErrorUnprocessableEntity) {
                        for (e in err.errorDetailsList) {
                            view!!.onRegistrationFailure(GRPCError(e.getDetails(0)))
                            return@doAsync
                        }
                    }

                    for (e in res.errorsList) {
                        view!!.onRegistrationFailure(GRPCError(e.details))
                        return@doAsync
                    }
                }
                if (isViewAttached) {
                    view!!.onRegistrationFailure(GRPCError("Something went wrong !!!"))
                }
            } catch (e: Exception) {
                if (isViewAttached) {
                    view!!.onRegistrationFailure(NetworkError())
                }
            }
        }
    }
}
