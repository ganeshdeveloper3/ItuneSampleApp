package com.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItuneApiResponse(
    @Json(name = "resultCount")
    val resultCount: Int,
    @Json(name = "results")
    val results: List<Result>
)