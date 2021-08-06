package com.jdavila.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteConfigResponse(
    @Json(name = "update_url")
    val updateUrl: String,
    @Json(name = "min_version")
    val minVersion: String,
    @Json(name = "max_version")
    val maxVersion: String
)
