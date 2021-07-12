package com.api.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ItuneClient {

val retrofit = Retrofit.Builder()
    .baseUrl(" https://itunes.apple.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

    val api = retrofit.create(ItuneApi::class.java)

}