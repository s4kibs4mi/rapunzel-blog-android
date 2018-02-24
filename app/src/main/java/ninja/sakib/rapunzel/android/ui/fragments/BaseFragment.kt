package ninja.sakib.rapunzel.android.ui.fragments

import com.hannesdorfmann.mosby.mvp.MvpFragment
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView

abstract class BaseFragment<V : MvpView, P : MvpPresenter<V>> : MvpFragment<V, P>() {

}
