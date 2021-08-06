package com.jdavila.forceupdate.di.module

import com.jdavila.data.remote.UpdateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideUpdateService(retrofit: Retrofit): UpdateService = retrofit.create(UpdateService::class.java)
}