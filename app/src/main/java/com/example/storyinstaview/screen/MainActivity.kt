package com.example.storyinstaview.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storyinstaview.adapter.StoryUserAdapter
import com.example.storyinstaview.model.StoryUserModel
import com.example.storyinstaview.databinding.ActivityMainBinding
import com.example.storyinstaview.model.Stories

class MainActivity : AppCompatActivity() {

    private var position: Int? = 0
    private var storyUserModel: StoryUserModel? = null
    private var storyUserAdapter: StoryUserAdapter? = null
    private var storyUsersList: ArrayList<StoryUserModel>? = null
    lateinit var binding: ActivityMainBinding
    companion object{
        val LAUNCH_STORY_DISPLAY_ACTIVITY = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storyUsersList = getUsersList()
        initRV(storyUsersList!!)
    }

    private fun getUsersList(): ArrayList<StoryUserModel> {
        val storyUserList = ArrayList<StoryUserModel>()

        val storyUserModel = StoryUserModel()
        storyUserModel.userName = "User1"
        storyUserModel.userProfileUrl = "https://randomuser.me/api/portraits/women/1.jpg"
        storyUserModel.viewIndex = 0
        storyUserModel.isStorySeen = false

        val stories = Stories()
        stories.storyUrl = "https://player.vimeo.com/external/403295268.sd.mp4?s=3446f787cefa52e7824d6ce6501db5261074d479&profile_id=165&oauth2_token_id=57447761"
        stories.userName = "User1"
        stories.isStorySeen = false
        stories.storyDate = System.currentTimeMillis() - (1 * (24 - 0) * 60 * 60 * 1000)


        val stories1 = Stories()
        stories1.storyUrl = "https://randomuser.me/api/portraits/women/2.jpg"
        stories1.userName = "User1"
        stories1.isStorySeen = false
        stories1.storyDate = System.currentTimeMillis() - (1 * (24 - 1) * 60 * 60 * 1000)


        val stories2 = Stories()
        stories2.storyUrl = "https://player.vimeo.com/external/409206405.sd.mp4?s=0bc456b6ff355d9907f285368747bf54323e5532&profile_id=165&oauth2_token_id=57447761"
        stories2.userName = "User1"
        stories2.isStorySeen = false
        stories2.storyDate = System.currentTimeMillis() - (1 * (24 - 2) * 60 * 60 * 1000)

        val storiesList : MutableList<Stories> = mutableListOf()
        storiesList.add(stories)
        storiesList.add(stories1)
        storiesList.add(stories2)

        storyUserModel.storiesList = storiesList

        storyUserList.add(storyUserModel)


        val storyUserModel1 = StoryUserModel()
        storyUserModel1.userName = "User2"
        storyUserModel1.userProfileUrl = "https://images.pexels.com/photos/2458400/pexels-photo-2458400.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        storyUserModel.viewIndex = 0
        storyUserModel1.isStorySeen = false

        val stories3= Stories()
        stories3.storyUrl = "https://images.pexels.com/photos/134020/pexels-photo-134020.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        stories3.userName = "User2"
        stories3.isStorySeen = false
        stories3.storyDate = System.currentTimeMillis() - (1 * (24 - 3) * 60 * 60 * 1000)


        /*val stories4 = Stories()
        stories4.storyUrl = "https://randomuser.me/api/portraits/women/2.jpg"
        stories4.userName = "User2"
        stories4.storyDate = System.currentTimeMillis() - (1 * (24 - 4) * 60 * 60 * 1000)*/


        val stories5 = Stories()
        stories5.storyUrl = "https://player.vimeo.com/external/422787651.sd.mp4?s=ec96f3190373937071ba56955b2f8481eaa10cce&profile_id=165&oauth2_token_id=57447761"
        stories5.userName = "User2"
        stories5.isStorySeen = false
        stories5.storyDate = System.currentTimeMillis() - (1 * (24 - 5) * 60 * 60 * 1000)

        val storiesList1 : MutableList<Stories> = mutableListOf()
        storiesList1.add(stories3)
        //storiesList1.add(stories4)
        storiesList1.add(stories5)

        storyUserModel1.storiesList = storiesList1

        storyUserList.add(storyUserModel1)


        val storyUserModel2 = StoryUserModel()
        storyUserModel2.userName = "User3"
        storyUserModel2.userProfileUrl = "https://randomuser.me/api/portraits/men/6.jpg"
        storyUserModel.viewIndex = 0
        storyUserModel2.isStorySeen = false

        val stories6= Stories()
        stories6.storyUrl = "https://player.vimeo.com/external/394678700.sd.mp4?s=353646e34d7bde02ad638c7308a198786e0dff8f&profile_id=165&oauth2_token_id=57447761"
        stories6.userName = "User3"
        stories6.isStorySeen = false
        stories6.storyDate = System.currentTimeMillis() - (1 * (24 - 6) * 60 * 60 * 1000)


        val stories7 = Stories()
        stories7.storyUrl = "https://images.pexels.com/photos/1612461/pexels-photo-1612461.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        stories7.userName = "User3"
        stories7.isStorySeen = false
        stories7.storyDate = System.currentTimeMillis() - (1 * (24 - 7) * 60 * 60 * 1000)


        val stories8 = Stories()
        stories8.storyUrl = "https://images.pexels.com/photos/2260800/pexels-photo-2260800.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
        stories8.userName = "User3"
        stories8.isStorySeen = false
        stories8.storyDate = System.currentTimeMillis() - (1 * (24 - 8) * 60 * 60 * 1000)

        val storiesList2 : MutableList<Stories> = mutableListOf()
        storiesList2.add(stories6)
        storiesList2.add(stories7)
        storiesList2.add(stories8)

        storyUserModel2.storiesList = storiesList2

        storyUserList.add(storyUserModel2)

        return storyUserList
    }

    private fun initRV(storyUsersList: MutableList<StoryUserModel>) {

        storyUserAdapter = StoryUserAdapter(storyUsersList, this)
        binding.rvStoryUser.adapter = storyUserAdapter


        storyUserAdapter?.onSelectionChangeListener = { storyUser : StoryUserModel, position :Int ->
           val intent = Intent(this, StoryDisplayActivity::class.java)
            intent.putExtra("position",position)
            intent.putParcelableArrayListExtra("list",storyUsersList as ArrayList<StoryUserModel>)
            startActivityForResult(intent, LAUNCH_STORY_DISPLAY_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LAUNCH_STORY_DISPLAY_ACTIVITY && resultCode == RESULT_OK){
            if(data?.hasExtra("storyUserList") == true){
                storyUsersList = data.getParcelableArrayListExtra("storyUserList")


                storyUsersList?.forEachIndexed { index, storyUserModel ->
                    //storySeenList?.size == storyUserModel.storiesList?.size
                    var storySeenList :  MutableList<Boolean>? = mutableListOf()
                    storyUserModel.storiesList?.forEachIndexed { index, stories ->
                        if(stories.isStorySeen==true)
                            storySeenList?.add(stories.isStorySeen!!)
                    }

                    storyUserModel.isStorySeen = storyUserModel.storiesList?.size == storySeenList?.size

                }
                storyUserAdapter?.updateList(storyUsersList)
            }
           /* if(data?.hasExtra("position") == true){
               position = data.extras?.getInt("position")
                //storyUsersList[position].storiesList
                var storySeenList :  MutableList<Boolean>? = mutableListOf()
                storyUsersList?.get(position!!)?.storiesList?.forEachIndexed { index, stories ->
                    if(stories.isStorySeen==true)
                        storySeenList?.add(stories.isStorySeen!!)
                }

                storyUsersList?.get(position!!)?.isStorySeen = storyUsersList?.get(position!!)?.storiesList?.size == storySeenList?.size

                storyUserAdapter?.updateList(storyUsersList)
            }*/
        }
    }

    override fun onResume() {
        super.onResume()
    }

}