package com.example.storyinstaview.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

class StoryUser : ArrayList<StoryUser.StoryUserItem>(){
    @Keep
    data class StoryUserItem(
        @SerializedName("img")
        var img: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("p_img")
        var pImg: String? = "",
        @SerializedName("seen")
        var seen: Boolean? = false,
        @SerializedName("story")
        var story: List<Story>? = listOf(),
        @SerializedName("user_id")
        var userId: Int? = 0
    ) {
        @Keep
        data class Story(
            @SerializedName("duration")
            var duration: Double? = 0.0,
            @SerializedName("seen")
            var seen: Boolean? = false,
            @SerializedName("story_id")
            var storyId: Int? = 0,
            @SerializedName("type")
            var type: String? = "",
            @SerializedName("url")
            var url: String? = ""
        )
    }
}