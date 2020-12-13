package com.dicoding.picodiploma.submission

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter (private val Context: Context,frag: FragmentManager) : FragmentPagerAdapter(frag, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object{
        private val TAB_TITLES = intArrayOf(
                R.string.follower,
                R.string.following
        )
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment?=null
        when(position){
            0->fragment=FollowerFragment()
            1->fragment=FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return Context.resources.getString(TAB_TITLES[position])
    }
}