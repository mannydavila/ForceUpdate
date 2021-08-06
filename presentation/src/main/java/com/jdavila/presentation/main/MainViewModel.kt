package com.jdavila.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdavila.data.repository.AppUpdateRepository
import com.jdavila.domain.model.RemoteConfig
import com.jdavila.domain.util.ObjectMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppUpdateRepository
) : ViewModel() {

    val isUpdateRequired = MutableLiveData<Boolean>()
    val isUpdateRecommended = MutableLiveData<Boolean>()

    // region Private Methods
    private fun determineUpdateType(remoteConfig: RemoteConfig, currentVersion: String?) {
        currentVersion?.let {
            if (currentVersion < remoteConfig.minVersion) {
                isUpdateRequired.value = true
            } else if (currentVersion < remoteConfig.maxVersion) {
                isUpdateRecommended.value = true
            }
        }
    }
    // endregion

    // region Public Methods
    fun checkForUpdate(currentVersion: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            var remoteConfig: RemoteConfig? = null
            try {
                val remoteConfigResponse = repository.getRemoteConfig()
                remoteConfig = ObjectMapper.mapRemoteConfigResponse(remoteConfigResponse)
            } catch (exception: Exception) {
                Log.d(this.javaClass.name, "Exception encountered fetching remote config ${exception.message}")
            } finally {
                if (remoteConfig != null) {
                    launch(Dispatchers.Main) {
                        determineUpdateType(remoteConfig, currentVersion)
                    }
                }
            }
        }
    }
    // endregion
}