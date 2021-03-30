package com.example.storyinstaview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
    var userName : String? = "",
    var storyUrl : String? = "",
    var storyDate : Long ? = 0L,
    var isStorySeen : Boolean ? = false,
    var commentsList : MutableList<CommentsModel>? = mutableListOf()
) :Parcelable
{
    fun checkIsVideo() : Boolean{
        var isVideo : Boolean ? = false
        isVideo = storyUrl?.contains(".mp4") == true || storyUrl?.contains(".m3u8")==true || storyUrl?.contains(".mpd")==true
        return isVideo
    }
}