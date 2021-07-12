package com.itunesampleapp.ui.video
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.api.models.Result
import com.itunesampleapp.data.ItuneVideoRepo
import kotlinx.coroutines.launch

/*
*
*
* */

class VideoViewModel(val ituneVideoRepo: ItuneVideoRepo) : ViewModel() {

    private val _video = MutableLiveData<List<Result>>()
    val video: LiveData<List<Result>> = _video


    fun fetchVideo() {
        viewModelScope.launch {
            val videos = ituneVideoRepo.getVideos().body()?.let {
                _video.postValue(it.results)
                Log.d("FEED ", "feed fetched ${it.resultCount}")
            }
        }
    }
    fun saveArticle(result: Result) = viewModelScope.launch {
        ituneVideoRepo.upsert(result)
    }
    fun getSavedNews() = ituneVideoRepo.getSavedNews()
}