package com.example.storyinstaview.screen

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.example.storyinstaview.R
import com.example.storyinstaview.adapter.CommentsAdapter
import com.example.storyinstaview.databinding.FragmentStoryDisplayBinding
import com.example.storyinstaview.model.CommentsModel
import com.example.storyinstaview.model.Stories
import com.example.storyinstaview.model.StoryUserModel
import com.example.storyinstaview.momentz.Momentz
import com.example.storyinstaview.momentz.MomentzCallback
import com.example.storyinstaview.momentz.MomentzView
import com.example.storyinstaview.utils.BROADCAST_STORY_END
import com.example.storyinstaview.utils.BROADCAST_STORY_PREVIOUS
import com.example.storyinstaview.utils.toPixel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.util.*


class StoryDisplayFragment : Fragment(), MomentzCallback {

    private var commentsAdapter: CommentsAdapter? = null
    private var momentz: Momentz? = null
    lateinit var binding: FragmentStoryDisplayBinding

    //private var storyUserList : MutableList<StoryUserModel>? = mutableListOf()
    private val position: Int by
    lazy { arguments?.getInt(EXTRA_POSITION) ?: 0 }


    private val storyUser: StoryUserModel by
    lazy {
        (arguments?.getParcelable<StoryUserModel>(
                EXTRA_STORY_USER
        ) as StoryUserModel)
    }

    private val storyUserList: ArrayList<StoryUserModel> by
    lazy {
        (arguments?.getParcelableArrayList<StoryUserModel>(
                EXTRA_STORY_USER_LIST
        ) as ArrayList<StoryUserModel>)
    }

    private val stories: MutableList<Stories> by
    lazy {
        storyUser.storiesList!!
    }


    private var simpleExoPlayer: SimpleExoPlayer? = null
    private lateinit var mediaDataSourceFactory: DataSource.Factory
    private var pageViewOperator: PageViewOperator? = null
    private var counter = 0
    private var pressTime = 0L
    private var limit = 500L
    private var onResumeCalled = false
    private var onVideoPrepared = false
    private var commentsList: MutableList<CommentsModel>? = mutableListOf()
    var listItem = MutableLiveData<Int>()
    var newListItem : Int ? = 0

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryDisplayBinding.inflate(layoutInflater, container, false)

        val textView = TextView(requireActivity())
        textView.text = "Hello, You can display TextViews"
        textView.textSize = 20f.toPixel(requireActivity()).toFloat()
        textView.gravity = Gravity.CENTER
        textView.setTextColor(Color.parseColor("#ffffff"))

        // show an imageview be loaded from file
        val locallyLoadedImageView = ImageView(requireActivity())
        locallyLoadedImageView.setImageDrawable(
                ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.bieber
                )
        )
        if(isAdded) {

            //image to be loaded from the internet
            val internetLoadedImageView = ImageView(requireActivity())

            //video to be loaded from the internet
            val internetLoadedVideo = VideoView(requireActivity())

            val listOfViews: MutableList<MomentzView> = mutableListOf()
            stories.forEachIndexed { index, stories ->
                val cal: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
                    timeInMillis = stories.storyDate!!
                }
                binding.storyDisplayTime.text = DateFormat.format("MM-dd-yyyy HH:mm:ss", cal).toString()
                if (stories.checkIsVideo()) {
                    listOfViews.add(MomentzView(internetLoadedVideo, 60, stories.storyUrl.toString(), stories.isStorySeen!!, stories))
                } else {
                    listOfViews.add(MomentzView(internetLoadedImageView, 10, stories.storyUrl.toString(), stories.isStorySeen!!, stories))
                }
            }

            //if (isAdded && isVisible) {
            momentz = Momentz(requireActivity(), listOfViews, binding.container, this, storyUser.viewIndex!!, listItem)
            momentz?.start()
            // }
        }
        Glide.with(this).load(storyUser.userProfileUrl).circleCrop().into(binding.storyDisplayProfilePicture)
        binding.storyDisplayNick.text = storyUser.userName
        binding.ivCloseStory.setOnClickListener {
            finishActivityWithResult()
        }


        val bottomSheetBehavior = BottomSheetBehavior.from<View>(binding.bottomSheet.commentsLayout)

        binding.bottomSheet.etComment.clearFocus()

        binding.bottomSheet.etComment.setOnClickListener(View.OnClickListener {
            if (binding.bottomSheet.etComment.isCursorVisible) {
                momentz?.pause(false)
            } else {
                binding.bottomSheet.etComment.requestFocus()
                momentz?.pause(false)
            }
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        )

        binding.container.setOnClickListener {
            binding.bottomSheet.etComment.clearFocus()
            momentz?.resume()
        }
        initRV()

        stories[newListItem!!].commentsList!!.clear()
        commentsAdapter?.updateList(stories[newListItem!!].commentsList!!)
        binding.bottomSheet.btnSendComment.setOnClickListener {

            //add item to recyclerview
            val commentsModel = CommentsModel()
            commentsModel.userName = binding.bottomSheet.etComment.text.toString()
            commentsModel.userProfileUrl = storyUser.userProfileUrl

            //commentsList?.add(commentsModel)
            listItem.observe(this, {
                Log.d("hi::", "onCreateView: story position:" + it)

            })
            stories[newListItem!!].commentsList?.add(commentsModel)
            //stories[0].commentsList?.addAll(commentsList!!)
            commentsAdapter?.updateList(stories[newListItem!!].commentsList!!)
            binding.bottomSheet.rvComments.scrollToPosition(commentsAdapter?.itemCount!! - 1)
            binding.bottomSheet.etComment.setText("")
            //commentsAdapter?.notifyDataSetChanged()

        }
        checkIsKeyBoardOpen()
        return (binding.root)
    }

    private fun checkIsKeyBoardOpen() {
        val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isActive) {
            //return true
            Log.e("hi::", "checkIsKeyBoardOpen: Keyboard  showing")
            //writeToLog("Software Keyboard was shown")
        } else {
            //return false
            Log.e("hi::", "checkIsKeyBoardOpen: Keyboard not showing")
            //writeToLog("Software Keyboard was not shown")
        }
    }

    private fun initRV() {

        commentsAdapter = CommentsAdapter(mutableListOf(), requireActivity())
        binding.bottomSheet.rvComments.adapter = commentsAdapter


        /* storyUserAdapter?.onSelectionChangeListener = { storyUser : StoryUserModel, position :Int ->
             val intent = Intent(this, StoryDisplayActivity::class.java)
             intent.putExtra("position",position)
             intent.putParcelableArrayListExtra("list",storyUsersList as ArrayList<StoryUserModel>)
             startActivityForResult(intent, MainActivity.LAUNCH_STORY_DISPLAY_ACTIVITY)
         }*/

    }


    private fun savePosition(pos: Int) {
        StoryDisplayActivity.progressState.put(position, pos)
    }

    private fun restorePosition(): Int {
        return StoryDisplayActivity.progressState.get(position)
    }

    companion object {
        private const val EXTRA_POSITION = "EXTRA_POSITION"
        private const val EXTRA_STORY_USER = "EXTRA_STORY_USER"
        private const val EXTRA_STORY_USER_LIST = "EXTRA_STORY_USER_LIST"
        fun newInstance(
                position: Int,
                story: StoryUserModel,
                storyUserList: ArrayList<StoryUserModel>
        ): StoryDisplayFragment {
            return StoryDisplayFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_POSITION, position)
                    putParcelable(EXTRA_STORY_USER, story)
                    putParcelableArrayList(EXTRA_STORY_USER_LIST, storyUserList)
                }

            }
        }
    }



    override fun onNextCalled(view: View, momentz: Momentz, index: Int, currentUrl: String, viewedIndex: Int) {
        Log.d("hi::", "onNextCalled: story next position : " + index + "::" + listItem.value)
        newListItem = index
        //if(newListItem!! > 0) {
            stories[newListItem!!].commentsList?.clear()
            commentsAdapter?.updateList(stories[newListItem!!].commentsList)
        //}
       // commentsAdapter?.notifyDataSetChanged()
        if (view is VideoView) {
            momentz.pause(true)
            //storyUserList[position].viewIndex = index+ 1
            if(storyUserList[position].isStorySeen == true){
                storyUserList[position].viewIndex = 0
                storyUserList[position].isStorySeen = true
            } else {
                if (storyUserList[position].viewIndex == stories.size) {
                    storyUserList[position].viewIndex = 0
                    storyUserList[position].isStorySeen = true
                } else {
                    storyUserList[position].viewIndex = viewedIndex
                }
            }
            /*  if(storyUser.viewIndex == stories.size){
                  storyUser.viewIndex = 0
              }else{
                  storyUser?.viewIndex!! + 1
              }*/
            //stories[index].isStorySeen = true
            playVideo(view, index, momentz, currentUrl)
        } else if ((view is ImageView)) {
            momentz.pause(true)
            if(storyUserList[position].isStorySeen == true){
                storyUserList[position].viewIndex = 0
                storyUserList[position].isStorySeen = true
            } else {
                if (storyUserList[position].viewIndex == stories.size) {
                    storyUserList[position].viewIndex = 0
                    storyUserList[position].isStorySeen = true
                } else {
                    storyUserList[position].viewIndex = viewedIndex
                }
            }
            /* if(storyUser.viewIndex == stories.size){
                 storyUser.viewIndex = 0
             }else{
                 storyUser?.viewIndex!! + 1
             }*/
            //stories[index].isStorySeen = true
            Picasso.get()
                    .load(currentUrl)
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(view, object : Callback {
                        override fun onSuccess() {
                            momentz.resume()
                            //Toast.makeText(requireActivity(), "Image loaded from the internet", Toast.LENGTH_LONG).show()
                        }

                        override fun onError(e: Exception?) {
                            //Toast.makeText(requireActivity(), e?.localizedMessage, Toast.LENGTH_LONG).show()
                            e?.printStackTrace()
                        }
                    })
        }
    }

    override fun previous() {
        storyUserList[position].storiesList = stories
        val intent = Intent(BROADCAST_STORY_PREVIOUS)
        Log.e("TAG", "done: ${position}")
        intent.putExtra("KeyPrev", position)
        intent.putExtra("storyUser", storyUser)
        intent.putParcelableArrayListExtra("stories",
                stories as ArrayList<out Parcelable?>?
        )
        intent.putParcelableArrayListExtra("storyUserList", storyUserList)
        intent.putExtra("position", position)
        if (isAdded)
            requireActivity().setResult(RESULT_OK, intent)
        activity?.applicationContext?.let {
            LocalBroadcastManager.getInstance(it)
                    .sendBroadcast(intent)
        }
    }

    override fun done() {
        storyUserList[position].storiesList = stories
        val intent = Intent(BROADCAST_STORY_END)
        Log.e("TAG", "done: ${position}")
        intent.putExtra("KeyNext", position)
        intent.putExtra("storyUser", storyUser)
        intent.putParcelableArrayListExtra("stories",
                stories as ArrayList<out Parcelable?>?
        )
        intent.putParcelableArrayListExtra("storyUserList", storyUserList)
        intent.putExtra("position", position)
        if (isAdded)
            requireActivity().setResult(RESULT_OK, intent)
        activity?.applicationContext?.let {
            LocalBroadcastManager.getInstance(it)
                    .sendBroadcast(intent)
        }
        //Toast.makeText(requireActivity(), "Finished!", Toast.LENGTH_LONG).show()
        /*if(isAdded)
            finishActivityWithResult()*/
        //requireActivity().finish()
    }

    fun finishActivityWithResult() {

        storyUserList[position].storiesList = stories
        val intent = Intent()
        intent.putExtra("storyUser", storyUser)
        intent.putParcelableArrayListExtra("stories",
                stories as ArrayList<out Parcelable?>?
        )
        intent.putParcelableArrayListExtra("storyUserList", storyUserList)
        intent.putExtra("position", position)
        requireActivity().setResult(RESULT_OK, intent)
        requireActivity().finish()
        // finishActivityWithResult()
    }


    private fun playVideo(videoView: VideoView, index: Int, momentz: Momentz, currentUrl: String) {

        val str = currentUrl
        val uri = Uri.parse(str)

        videoView.setVideoURI(uri)

        videoView.requestFocus()
        videoView.start()

        videoView.setOnInfoListener(object : MediaPlayer.OnInfoListener {
            override fun onInfo(mp: MediaPlayer?, what: Int, extra: Int): Boolean {

                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    // Here the video starts
                    momentz.editDurationAndResume(index, (videoView.duration) / 1000)
                    //Toast.makeText(requireActivity(), "Video loaded from the internet", Toast.LENGTH_LONG).show()
                    return true
                }
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //image to be loaded from the internet
       /* val internetLoadedImageView = ImageView(requireActivity())

        //video to be loaded from the internet
        val internetLoadedVideo = VideoView(requireActivity())

        val listOfViews: MutableList<MomentzView> = mutableListOf()
        stories.forEachIndexed { index, stories ->
            val cal: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
                timeInMillis = stories.storyDate!!
            }
            binding.storyDisplayTime.text = DateFormat.format("MM-dd-yyyy HH:mm:ss", cal).toString()
            if (stories.checkIsVideo()) {
                listOfViews.add(MomentzView(internetLoadedVideo, 60, stories.storyUrl.toString(), stories.isStorySeen!!, stories))
            } else {
                listOfViews.add(MomentzView(internetLoadedImageView, 10, stories.storyUrl.toString(), stories.isStorySeen!!, stories))
            }
        }

        //if (isAdded && isVisible) {
        //if(momentz==null) {
            momentz = Momentz(requireActivity(), listOfViews, binding.container, this, storyUser.viewIndex!!, listItem)
            momentz?.start()*/
        //}else{
        //    momentz = Momentz(requireActivity(), listOfViews, binding.container, this, storyUser.viewIndex!!, listItem)
        //}
        // }

    }



}