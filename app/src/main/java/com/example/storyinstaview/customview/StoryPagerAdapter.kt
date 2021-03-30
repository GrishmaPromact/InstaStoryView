package com.example.storyinstaview.customview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.storyinstaview.screen.StoryDisplayFragment
import com.example.storyinstaview.model.StoryUserModel

class StoryPagerAdapter constructor(fragmentManager: FragmentManager, private val storyList: ArrayList<StoryUserModel>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return storyList.size
    }

    override fun getItem(position: Int): Fragment {
           return StoryDisplayFragment.newInstance(position, storyList[position],storyList)
    }

    fun findFragmentByPosition(viewPager: ViewPager, position: Int): Fragment? {
        try {
            val f = instantiateItem(viewPager, position)
            return f as? Fragment
        } finally {
            finishUpdate(viewPager)
        }
    }
}