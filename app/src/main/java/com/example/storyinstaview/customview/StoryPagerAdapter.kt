package com.example.storyinstaview.customview

import android.database.DataSetObserver
import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.storyinstaview.model.StoryUserModel
import com.example.storyinstaview.screen.StoryDisplayFragment

class StoryPagerAdapter constructor(fragmentManager: FragmentManager, private val storyList: ArrayList<StoryUserModel>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return storyList.size
    }

  /*  override fun getItem(position: Int): Fragment {
        TODO("Not yet implemented")
    }*/

    override fun getItem(position: Int): Fragment {
        return if(position == 0) {
            Log.d("hi::", "getItem: " + position)
            StoryDisplayFragment.newInstance(position, storyList[position], storyList)
        }else{
            Log.d("hi::", "getItem: " + position)
            StoryDisplayFragment.newInstance(position, storyList[position], storyList)
        }
    }


    fun findFragmentByPosition(viewPager: ViewPager, position: Int): Fragment? {
        //viewPager.currentItem
        try {
            val f = instantiateItem(viewPager, position)
            return f as? Fragment
        } finally {
            finishUpdate(viewPager)
        }
    }

}