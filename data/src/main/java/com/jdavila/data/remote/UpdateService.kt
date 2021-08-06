package com.jdavila.data.remote

import com.jdavila.data.entity.RemoteConfigResponse
import retrofit2.http.GET

interface UpdateService {

    @GET("remoteConfig")
    suspend fun getRemoteConfig() : RemoteConfigResponse
}