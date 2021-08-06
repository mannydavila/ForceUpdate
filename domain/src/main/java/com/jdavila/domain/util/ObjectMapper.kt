package com.jdavila.domain.util

import com.jdavila.data.entity.RemoteConfigResponse
import com.jdavila.domain.model.RemoteConfig


class ObjectMapper {

    companion object {
        fun mapRemoteConfigResponse(remoteConfigResponse: RemoteConfigResponse) : RemoteConfig =
            RemoteConfig(remoteConfigResponse.updateUrl,
                remoteConfigResponse.minVersion,
                remoteConfigResponse.maxVersion)
    }
}
