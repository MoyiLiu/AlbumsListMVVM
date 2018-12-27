package com.moyiliu.albumslistmvvm.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moyiliu.albumslistmvvm.BuildConfig
import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.constant.BASE_URL
import com.moyiliu.albumslistmvvm.di.annotation.AlbumServer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
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
    @AlbumServer
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG)
                    addInterceptor(
                        HttpLoggingInterceptor()
                            .apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
            }
            .callTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    @AlbumServer
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @AlbumServer
    fun provideAlbumApi(@AlbumServer retrofit: Retrofit): AlbumApi =
        retrofit.create()
}