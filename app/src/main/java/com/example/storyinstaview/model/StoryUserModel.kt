package com.example.storyinstaview.model

import android.os.Parcelable
import com.example.storyinstaview.model.Stories
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoryUserModel(
    var userName : String? = "",
    var userProfileUrl : String? = "",
    var isStorySeen : Boolean ? = false,
    var viewIndex : Int ? = 0,
    var storiesList :  MutableList<Stories>? = mutableListOf()) : Parcelable
{}