package com.jdavila.data.repository

import com.jdavila.data.remote.UpdateService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUpdateRepository @Inject constructor(
    private val service: UpdateService) {

    suspend fun getRemoteConfig() = service.getRemoteConfig()
}