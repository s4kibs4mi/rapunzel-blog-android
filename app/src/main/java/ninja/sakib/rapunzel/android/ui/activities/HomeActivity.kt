package ninja.sakib.rapunzel.android.ui.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_home.*
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.ui.adapters.HomeViewPagerAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.image
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onPageChangeListener

class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewPager: ViewPager
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    private lateinit var navPostsIcon: ImageView
    private lateinit var navUsersIcon: ImageView
    private lateinit var navUserProfileIcon: ImageView

    private lateinit var navPosts: RelativeLayout
    private lateinit var navUsers: RelativeLayout
    private lateinit var navUserProfile: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        homeViewPagerAdapter = HomeViewPagerAdapter(supportFragmentManager)

        navPostsIcon = find(R.id.homeNavPostsIcon)
        navUsersIcon = find(R.id.homeNavUsersIcon)
        navUserProfileIcon = find(R.id.homeNavUserProfileIcon)

        navPosts = find(R.id.homeNavPosts)
        navUsers = find(R.id.homeNavUsers)
        navUserProfile = find(R.id.homeNavUserProfile)

        onPostListSelected()

        homeViewPager = find(R.id.homeViewPager)
        homeViewPager.adapter = homeViewPagerAdapter

        homeViewPager.onPageChangeListener {
            onPageSelected { i ->
                onViewPagerPageSelected(i)
            }
        }
        navPosts.onClick {
            homeViewPager.setCurrentItem(0, true)
            onPostListSelected()
        }
        navUsers.onClick {
            homeViewPager.setCurrentItem(1, true)
            onUserListSelected()
        }
        navUserProfile.onClick {
            homeViewPager.setCurrentItem(2, true)
            onUserProfileSelected()
        }
    }

    private fun onViewPagerPageSelected(selectedPage: Int) {
        when (selectedPage) {
            0 -> {
                onPostListSelected()
            }
            1 -> {
                onUserListSelected()
            }
            2 -> {
                onUserProfileSelected()
            }
        }
    }

    private fun onPostListSelected() {
        navPostsIcon.image = resources.getDrawable(R.drawable.ic_assignment_white_24dp)
        navUsersIcon.image = resources.getDrawable(R.drawable.ic_people_black_24dp)
        navUserProfileIcon.image = resources.getDrawable(R.drawable.ic_person_black_24dp)

        navPosts.background = resources.getDrawable(R.color.colorBackgroundBlue)
        navUsers.background = resources.getDrawable(R.color.colorBackgroundWhite)
        navUserProfile.background = resources.getDrawable(R.color.colorBackgroundWhite)
    }

    private fun onUserListSelected() {
        navPostsIcon.image = resources.getDrawable(R.drawable.ic_assignment_black_24dp)
        navUsersIcon.image = resources.getDrawable(R.drawable.ic_people_white_24dp)
        navUserProfileIcon.image = resources.getDrawable(R.drawable.ic_person_black_24dp)

        navPosts.background = resources.getDrawable(R.color.colorBackgroundWhite)
        navUsers.background = resources.getDrawable(R.color.colorBackgroundBlue)
        navUserProfile.background = resources.getDrawable(R.color.colorBackgroundWhite)
    }

    private fun onUserProfileSelected() {
        navPostsIcon.image = resources.getDrawable(R.drawable.ic_assignment_black_24dp)
        navUsersIcon.image = resources.getDrawable(R.drawable.ic_people_black_24dp)
        navUserProfileIcon.image = resources.getDrawable(R.drawable.ic_person_white_24dp)

        navPosts.background = resources.getDrawable(R.color.colorBackgroundWhite)
        navUsers.background = resources.getDrawable(R.color.colorBackgroundWhite)
        navUserProfile.background = resources.getDrawable(R.color.colorBackgroundBlue)
    }
}
