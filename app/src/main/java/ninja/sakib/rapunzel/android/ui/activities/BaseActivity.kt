package ninja.sakib.rapunzel.android.ui.activities

import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import ninja.sakib.rapunzel.android.R

abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {
    private lateinit var progressingDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showLoadingDialog() {
        progressingDialog = MaterialDialog.Builder(this)
                .content("Please wait...")
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .progress(true, 0)
                .build()
        progressingDialog.show()
    }

    fun hideLoadingDialog() {
        progressingDialog.hide()
    }
}
