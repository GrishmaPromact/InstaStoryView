package com.example.storyinstaview.screen

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.util.SparseIntArray
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.storyinstaview.R
import com.example.storyinstaview.customview.StoryPagerAdapter
import com.example.storyinstaview.databinding.ActivityStoryDisplayBinding
import com.example.storyinstaview.model.StoryUserModel
import com.example.storyinstaview.utils.BROADCAST_STORY_END
import com.example.storyinstaview.utils.BROADCAST_STORY_PREVIOUS
import com.example.storyinstaview.utils.CubeOutTransformer

class StoryDisplayActivity : AppCompatActivity(){

    private var position: Int?= 0
    private lateinit var pagerAdapter: StoryPagerAdapter
    private var currentPage: Int = 0
    var storyUserList = ArrayList<StoryUserModel>()

    lateinit var binding: ActivityStoryDisplayBinding

    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                BROADCAST_STORY_END -> {
                    val position = intent?.getIntExtra("KeyNext", -1)
                    Log.e("hi::", "onReceive: storyUserList " + storyUserList.size)
                    if (storyUserList.size > position + 1) {
                        binding.viewPager.currentItem = position + 1
                    } else {
                        finish()
                    }
                    Log.e("TAG", "onReceive: ${position + 1} ")
                }
                BROADCAST_STORY_PREVIOUS -> {
                    val position = intent?.getIntExtra("KeyPrev", -1)
                    Log.e("hi::", "onReceive: storyUserList " + storyUserList.size)
                    //if (storyUserList.size > position-1) {
                    if (position > 0) {
                        binding.viewPager.currentItem = position - 1
                        //fakeDrag(false)
                    }

                    Log.e("TAG", "onReceive: ${position + 1} ")
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoryDisplayBinding.inflate(layoutInflater)

        position = intent.extras?.getInt("position")
        currentPage = position!!

        if(intent.hasExtra("list")){
            storyUserList = intent.extras?.getParcelableArrayList<StoryUserModel>("list")!!
        }
        setContentView(binding.root)

        setUpPager()
        val myFilter = IntentFilter()
        myFilter.addAction(BROADCAST_STORY_END)
        myFilter.addAction(BROADCAST_STORY_PREVIOUS)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadCastReceiver, myFilter)
    }



    private fun setUpPager() {
        //val storyUserList = StoryGenerator.generateStories()*/
        val newStoryUserList : MutableList<StoryUserModel> = mutableListOf()

        for (i in position?.until(storyUserList.size)!!) {
            newStoryUserList.add(storyUserList[i])
        }

        pagerAdapter = StoryPagerAdapter(
            supportFragmentManager, storyUserList as ArrayList<StoryUserModel>
        )
        //binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.currentItem = currentPage

        binding.viewPager.setPageTransformer(
            true,
            CubeOutTransformer()
        )
       /* binding.viewPager.addOnPageChangeListener(object : PageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
            }

            override fun onPageScrollCanceled() {
                //currentFragment()?.resumeCurrentStory()
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })*/
    }


    companion object {
        val progressState = SparseIntArray()
    }

    private fun currentFragment(): StoryDisplayFragment? {
        return pagerAdapter.findFragmentByPosition(binding.viewPager, currentPage) as StoryDisplayFragment
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        currentFragment()?.finishActivityWithResult()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadCastReceiver)
    }


    /**
     * Change ViewPage sliding programmatically(not using reflection).
     * https://tech.dely.jp/entry/2018/12/13/110000
     * What for?
     * setCurrentItem(int, boolean) changes too fast. And it cannot set animation duration.
     */
    private var prevDragPosition = 0

    private fun fakeDrag(forward: Boolean) {
        if (prevDragPosition == 0 && binding.viewPager.beginFakeDrag()) {
            ValueAnimator.ofInt(0, binding.viewPager.width).apply {
                duration = 400L
                interpolator = FastOutSlowInInterpolator()
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        removeAllUpdateListeners()
                        if (binding.viewPager.isFakeDragging) {
                            binding.viewPager.endFakeDrag()
                        }
                        prevDragPosition = 0
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        removeAllUpdateListeners()
                        if (binding.viewPager.isFakeDragging) {
                            binding.viewPager.endFakeDrag()
                        }
                        prevDragPosition = 0
                    }

                    override fun onAnimationStart(p0: Animator?) {}
                })
                addUpdateListener {
                    if (!binding.viewPager.isFakeDragging) return@addUpdateListener
                    val dragPosition: Int = it.animatedValue as Int
                    val dragOffset: Float =
                            ((dragPosition - prevDragPosition) * if (forward) -1 else 1).toFloat()
                    prevDragPosition = dragPosition
                    binding.viewPager.fakeDragBy(dragOffset)
                }
            }.start()
        }
    }
}
