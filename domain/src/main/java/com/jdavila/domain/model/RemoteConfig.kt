package com.jdavila.domain.model

data class RemoteConfig(
    val updateUrl: String,
    val minVersion: String,
    val maxVersion: String
)
