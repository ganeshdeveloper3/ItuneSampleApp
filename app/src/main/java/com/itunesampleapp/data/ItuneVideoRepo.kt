package com.itunesampleapp.data

import com.api.services.ItuneClient
import com.api.models.Result
import com.itunesampleapp.db.VideoDatabase



class ItuneVideoRepo(val db:VideoDatabase) {
    val api = ItuneClient().api
    suspend fun getVideos() = api.getApiResponse()

    suspend fun upsert(result: Result) =db.getVideos().upsert(result)

    fun getSavedNews() = db.getVideos().getAllVideos()

}