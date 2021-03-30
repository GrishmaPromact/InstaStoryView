package com.example.storyinstaview.model

import android.os.Parcelable
import com.example.storyinstaview.model.Stories
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentsModel(
    var userName : String? = "",
    var userProfileUrl : String? = "") :Parcelable
{

}