package com.api

import com.api.services.ItuneClient
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ItuneClientTest {

    private val ituneClient = ItuneClient()
    @Test
    fun getVideos(){
        runBlocking {
            val videos = ituneClient.api.getApiResponse()
            Assert.assertNotNull(videos.body()?.results)
        }

    }
}