package ninja.sakib.rapunzel.android.ui.activities

import android.app.ProgressDialog
import android.os.Bundle
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import ninja.sakib.rapunzel.android.R

abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {
    private var loadingDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showLoadingDialog() {
        loadingDialog = ProgressDialog(this)
        loadingDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        loadingDialog!!.setMessage("Please wait...")
        loadingDialog!!.setCancelable(false)
        loadingDialog!!.setCanceledOnTouchOutside(false)
        loadingDialog!!.show()
    }

    fun hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
        }
    }

    override fun onStop() {
        hideLoadingDialog()
        super.onStop()
    }
}
