package com.jdavila.forceupdate.di.module

import com.jdavila.data.remote.UpdateService
import com.jdavila.data.repository.AppUpdateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(updateService: UpdateService) : AppUpdateRepository = AppUpdateRepository(updateService)
}