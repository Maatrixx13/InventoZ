package com.rafial.invento.di

import com.rafial.invento.api.ApiConfig
import com.rafial.invento.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

//    @Provides
//    @Singleton
//    fun provideApiServiceML(): ApiServiceML {
//        return ApiConfig.getApiServiceML()
//    }
}