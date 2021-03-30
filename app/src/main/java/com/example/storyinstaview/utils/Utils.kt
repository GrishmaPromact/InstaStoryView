package com.example.storyinstaview.utils

import android.content.Context
import android.util.TypedValue

fun Float.toPixel(mContext: Context): Int {
    val r = mContext.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        r.displayMetrics
    ).toInt()
}

const val BROADCAST_STORY_END = "BROADCAST_STORY_END"
const val BROADCAST_STORY_PREVIOUS = "BROADCAST_STORY_PREVIOUS"
