package com.itunesampleapp.ui.video

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itunesampleapp.data.ItuneVideoRepo


class VideoViewModelProviderFactory(
    val ituneVideoRepo: ItuneVideoRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(ituneVideoRepo) as T
    }
}