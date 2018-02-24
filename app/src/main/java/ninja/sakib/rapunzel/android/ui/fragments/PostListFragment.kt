package ninja.sakib.rapunzel.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.presenters.PostListPresenter
import ninja.sakib.rapunzel.android.ui.views.PostListView

class PostListFragment : BaseFragment<PostListView, PostListPresenter>(), PostListView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        return view
    }

    override fun createPresenter(): PostListPresenter {
        return PostListPresenter()
    }
}
