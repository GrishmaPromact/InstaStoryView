package com.example.storyinstaview.momentz

import android.view.View
import com.example.storyinstaview.model.Stories

data class MomentzView(
    val view: View,
    val durationInSeconds: Int,
    val url: String,
    var isSeen: Boolean,
    var stories: Stories
)