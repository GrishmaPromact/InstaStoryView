package com.example.storyinstaview.momentz

import android.content.Context
import android.content.Intent
import android.icu.text.LocaleDisplayNames
import android.os.Parcelable
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.VideoView
import android.view.GestureDetector.SimpleOnGestureListener
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.storyinstaview.R
import com.example.storyinstaview.databinding.ProgressStoryViewBinding
import com.example.storyinstaview.utils.BROADCAST_STORY_END
import java.lang.Exception


class Momentz : ConstraintLayout {
    var viewedIndex: Int
    private var currentlyShownIndex = 0
    private lateinit var currentView: View
    private var currentUrl : String ? = ""
    private var momentzViewList: List<MomentzView>
    private var libSliderViewList = mutableListOf<MyProgressBar>()
    private var momentzCallback : MomentzCallback
    private lateinit var view: ProgressStoryViewBinding
    private val passedInContainerView: ViewGroup
    private var mProgressDrawable : Int = R.drawable.green_lightgrey_drawable
    private var pausedState : Boolean = false
    lateinit var gestureDetector: GestureDetector
    var listItem : MutableLiveData<Int>

    constructor(
        context: Context,
        momentzViewList: List<MomentzView>,
        passedInContainerView: ViewGroup,
        momentzCallback: MomentzCallback,
        viewedIndex : Int,
        listItem: MutableLiveData<Int>,
        @DrawableRes mProgressDrawable : Int = R.drawable.green_lightgrey_drawable,

    ) : super(context) {
        this.momentzViewList = momentzViewList
        this.momentzCallback = momentzCallback
        this.passedInContainerView = passedInContainerView
        this.mProgressDrawable = mProgressDrawable
        this.viewedIndex = viewedIndex
        this.listItem = listItem
        initView(passedInContainerView)
        init()
    }

    private fun init() {
        momentzViewList.forEachIndexed { index, sliderView ->
            val myProgressBar = MyProgressBar(
                context,
                index,
                sliderView.durationInSeconds,
                object : ProgressTimeWatcher {
                    override fun onEnd(indexFinished: Int) {
                       // currentlyShownIndex = indexFinished + 1
                        next()
                    }
                },
                mProgressDrawable,momentzViewList)
            libSliderViewList.add(myProgressBar)
            view.linearProgressIndicatorLay.addView(myProgressBar)
        }
        //start()
    }

    fun callPause(pause : Boolean){
        try {
            if(pause){
                if(!pausedState){
                    this.pausedState = !pausedState
                    pause(false)
                }
            } else {
                if(pausedState){
                    this.pausedState = !pausedState
                    resume()
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun initView(passedInContainerView: ViewGroup) {
        view = ProgressStoryViewBinding.inflate(LayoutInflater.from(context),passedInContainerView,false)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )

        gestureDetector = GestureDetector(context, SingleTapConfirm())

        val touchListener = object  : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (gestureDetector.onTouchEvent(event)) {
                    // single tap
                    if(v?.id == view.rightLay.id){
                        next()
                    } else if(v?.id == view.leftLay.id){
                        prev()
                    }
                    return true
                } else {
                    // your code for move and drag
                    when(event?.action){
                        MotionEvent.ACTION_DOWN -> {
                            //before it was true
                            callPause(true)
                            return true
                        }

                        MotionEvent.ACTION_UP -> {
                            callPause(false)
                            return true
                        }
                        else -> return false
                    }
                }
            }
        }

//        view.leftLay.setOnClickListener { prev() }
//        view.rightLay.setOnClickListener { next() }
        view.leftLay.setOnTouchListener(touchListener)
        view.rightLay.setOnTouchListener(touchListener)
        //view.container.setOnTouchListener(touchListener)

        this.layoutParams = params
        this.passedInContainerView.addView(view.root)
    }

    fun show() {
        //currentlyShownIndex = viewedIndex
        view.loaderProgressbar.visibility = View.GONE
        if (currentlyShownIndex != 0) {
            for (i in 0..Math.max(0, currentlyShownIndex - 1)) {
                libSliderViewList[i].progress = 100
                libSliderViewList[i].cancelProgress()
            }
        }

        if (currentlyShownIndex != libSliderViewList.size - 1) {
            for (i in currentlyShownIndex + 1..libSliderViewList.size - 1) {
                libSliderViewList[i].progress = 0
                libSliderViewList[i].cancelProgress()
            }
        }

        currentView = momentzViewList[currentlyShownIndex].view

        currentUrl = momentzViewList[currentlyShownIndex].url

        libSliderViewList[currentlyShownIndex].startProgress()

        momentzViewList[currentlyShownIndex].isSeen = true
        momentzViewList[currentlyShownIndex].stories.isStorySeen = true


        listItem.postValue(currentlyShownIndex)
        //viewedIndex++
        momentzCallback.onNextCalled(currentView, this, currentlyShownIndex , currentUrl!!)

        view.currentlyDisplayedView.removeAllViews()
        view.currentlyDisplayedView.addView(currentView)
        val params = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT, 1f
        )
        //params.gravity = Gravity.CENTER_VERTICAL
        if(currentView is ImageView) {
            (currentView as ImageView).scaleType = ImageView.ScaleType.FIT_CENTER
            (currentView as ImageView).adjustViewBounds = true
        }
        currentView.layoutParams = params
    }

    fun start() {
//            Handler().postDelayed({
//                show()
//            }, 2000)
        //currentlyShownIndex = viewedIndex
        show()
    }

    fun editDurationAndResume(index: Int, newDurationInSecons : Int){
        view.loaderProgressbar.visibility = View.GONE
        libSliderViewList[index].editDurationAndResume(newDurationInSecons)
    }

    fun pause(withLoader : Boolean) {
        if(withLoader){
            view.loaderProgressbar.visibility = View.VISIBLE
        }
        libSliderViewList[currentlyShownIndex].pauseProgress()
        if(momentzViewList[currentlyShownIndex].view is VideoView){
            (momentzViewList[currentlyShownIndex].view as VideoView).pause()
        }
    }

    fun resume() {
        view.loaderProgressbar.visibility = View.GONE
        //if(libSliderViewList.size <= currentlyShownIndex-1) {
        //before there is no if condition
        if(currentlyShownIndex < libSliderViewList.size){
            libSliderViewList[currentlyShownIndex].resumeProgress()
            if (momentzViewList[currentlyShownIndex].view is VideoView) {
                (momentzViewList[currentlyShownIndex].view as VideoView).start()
            }
        }
    }

    private fun stop() {

    }

    fun next() {
        try {
            if (currentView == momentzViewList[currentlyShownIndex].view) {
                currentlyShownIndex++

              /*  if(viewedIndex == momentzViewList.size){
                    viewedIndex = 0
                }else{
                    viewedIndex++
                }*/

                if (momentzViewList.size <= currentlyShownIndex) {
                    finish()
                    return
                }
            }
            show()
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            //finish()
        }
    }

    private fun finish() {
        momentzCallback.done()
        for (progressBar in libSliderViewList) {
            progressBar.cancelProgress()
            progressBar.progress = 100
        }
    }

    fun prev() {
        try {
            Log.d("hi:::", "prevvvv: " +currentlyShownIndex)
            if (currentView == momentzViewList[currentlyShownIndex].view) {
                currentlyShownIndex--
                if (0 > currentlyShownIndex) {
                    currentlyShownIndex = 0
                    momentzCallback.previous()
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            currentlyShownIndex -= 2
        } finally {
            show()
        }
    }

    private inner class SingleTapConfirm : SimpleOnGestureListener() {

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            return true
        }
    }


}