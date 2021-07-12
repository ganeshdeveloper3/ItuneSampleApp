package com.api.services

import com.api.models.ItuneApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ItuneApi {

    @GET("search?term=Michael+jackson&media=musicVideo")
     suspend fun getApiResponse(): Response<ItuneApiResponse>
}