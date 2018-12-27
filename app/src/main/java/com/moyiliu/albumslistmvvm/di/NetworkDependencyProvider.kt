package com.moyiliu.albumslistmvvm.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.constant.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Dependency graph of Album Network
 */
@Module
class NetworkDependencyProvider {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    @AlbumServer
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @AlbumServer
    fun provideAlbumApi(@AlbumServer retrofit: Retrofit): AlbumApi =
        retrofit.create()
}