package com.example.storyinstaview.momentz

import android.view.View

interface MomentzCallback{
    fun done()

    fun onNextCalled(view: View, momentz: Momentz, index: Int, currentUrl: String, viewedIndex: Int)

    fun previous()
}
