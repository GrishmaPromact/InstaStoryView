package com.example.storyinstaview.utils

import com.example.storyinstaview.model.Stories
import com.example.storyinstaview.model.StoryUserModel

object StoryGenerator {

    fun generateStories(): ArrayList<StoryUserModel> {
        val storyUrls = ArrayList<String>()
        storyUrls.add("https://player.vimeo.com/external/403295268.sd.mp4?s=3446f787cefa52e7824d6ce6501db5261074d479&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/409206405.sd.mp4?s=0bc456b6ff355d9907f285368747bf54323e5532&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/403295710.sd.mp4?s=788b046826f92983ada6e5caf067113fdb49e209&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/394678700.sd.mp4?s=353646e34d7bde02ad638c7308a198786e0dff8f&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/405333429.sd.mp4?s=dcc3bdec31c93d87c938fc6c3ef76b7b1b188580&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/363465031.sd.mp4?s=15b706ccd3c0e1d9dc9290487ccadc7b20fff7f1&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://player.vimeo.com/external/422787651.sd.mp4?s=ec96f3190373937071ba56955b2f8481eaa10cce&profile_id=165&oauth2_token_id=57447761")
        storyUrls.add("https://images.pexels.com/photos/1433052/pexels-photo-1433052.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1366630/pexels-photo-1366630.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1067333/pexels-photo-1067333.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1122868/pexels-photo-1122868.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1837603/pexels-photo-1837603.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1612461/pexels-photo-1612461.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1591382/pexels-photo-1591382.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/53757/pexels-photo-53757.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500")
        storyUrls.add("https://images.pexels.com/photos/134020/pexels-photo-134020.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1367067/pexels-photo-1367067.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1420226/pexels-photo-1420226.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500")
        storyUrls.add("https://images.pexels.com/photos/2217365/pexels-photo-2217365.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/2260800/pexels-photo-2260800.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/1719344/pexels-photo-1719344.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/364096/pexels-photo-364096.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/3849168/pexels-photo-3849168.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/2953901/pexels-photo-2953901.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/3538558/pexels-photo-3538558.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        storyUrls.add("https://images.pexels.com/photos/2458400/pexels-photo-2458400.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")


        val userProfileUrls = ArrayList<String>()
        userProfileUrls.add("https://randomuser.me/api/portraits/women/1.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/1.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/2.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/2.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/3.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/3.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/4.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/4.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/5.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/5.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/6.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/6.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/7.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/7.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/8.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/8.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/9.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/9.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/10.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/10.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/women/11.jpg")
        userProfileUrls.add("https://randomuser.me/api/portraits/men/11.jpg")

        val storyUserList = ArrayList<StoryUserModel>()

        val storyUserModel = StoryUserModel()
        storyUserModel.userName = "User1"
        storyUserModel.userProfileUrl = "https://randomuser.me/api/portraits/women/1.jpg"
        storyUserModel.isStorySeen = false
        storyUserModel.viewIndex = 0


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
        storyUserModel1.isStorySeen = false
        storyUserModel1.viewIndex = 0

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
        storiesList1.add(stories3);
        //storiesList1.add(stories4)
        storiesList1.add(stories5)

        storyUserModel1.storiesList = storiesList1

        storyUserList.add(storyUserModel1)


        val storyUserModel2 = StoryUserModel()
        storyUserModel2.userName = "User3"
        storyUserModel2.userProfileUrl = "https://randomuser.me/api/portraits/men/6.jpg"
        storyUserModel2.isStorySeen = false
        storyUserModel2.viewIndex = 0

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
        storiesList2.add(stories6);
        storiesList2.add(stories7)
        storiesList2.add(stories8)

        storyUserModel2.storiesList = storiesList2

        storyUserList.add(storyUserModel2)

        /*for (i in 1..2) {
            val stories = ArrayList<Stories>()
            val storySize = 4
            for (j in 0 until storySize) {
                stories.add(
                    Stories( "username$i",
                        storyUrls[j],
                        System.currentTimeMillis() - (1 * (24 - j) * 60 * 60 * 1000)
                    )
                )
            }
            storyUserList.add(
                StoryUserModel(
                    "username$i",
                    userProfileUrls[i],
                    false,
                    stories
                )
            )
        }*/

        return storyUserList
    }
}