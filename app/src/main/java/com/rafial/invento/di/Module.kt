//package com.rafial.invento.di
//import com.rafial.invento.BuildConfig
//import com.rafial.invento.api.ApiService
//import com.rafial.invento.helper.NetworkInfo
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Module
//@InstallIn(SingletonComponent::class)
//object Module {
//
//
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        val interceptor = if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//        } else {
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
//        }
//
//        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(NetworkInfo.API_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient)
//            .build()
//    }
//
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//}