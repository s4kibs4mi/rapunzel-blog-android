package ninja.sakib.rapunzel.android.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ninja.sakib.rapunzel.android.ui.fragments.PostListFragment
import ninja.sakib.rapunzel.android.ui.fragments.UserListFragment
import ninja.sakib.rapunzel.android.ui.fragments.UserProfileFragment

/**
 * := Coded with love by Sakib Sami on 23/2/18.
 * := s4kibs4mi@gmail.com
 * := www.sakib.ninja
 * := Coffee : Dream : Code
 */

class HomeViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var navs = mutableListOf<Fragment>()

    init {
        navs.add(PostListFragment())
        navs.add(UserListFragment())
        navs.add(UserProfileFragment())
    }

    override fun getItem(position: Int): Fragment {
        return navs[position]
    }

    override fun getCount(): Int {
        return navs.size
    }
}
