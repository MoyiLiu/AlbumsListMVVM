package com.moyiliu.albumslistmvvm.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moyiliu.albumslistmvvm.AlbumApplication
import com.moyiliu.albumslistmvvm.BuildConfig
import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.constant.BASE_URL
import com.moyiliu.albumslistmvvm.db.AlbumDao
import com.moyiliu.albumslistmvvm.db.AppDatabase
import com.moyiliu.albumslistmvvm.di.annotation.AlbumServer
import dagger.Binds
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
 * Dependency graph of the application
 */
@Module(
    includes = [
        AppDependencyBinder::class,
        AlbumDependencyModule::class
    ]
)
class AppDependencyModule {
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
    fun provideRetrofit(gson: Gson, @AlbumServer client: OkHttpClient): Retrofit =
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

    @Singleton
    @Provides
    fun providesApplicationDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "album.db").build()

    @Provides
    @Singleton
    fun providesAlbumDao(appDatabase: AppDatabase): AlbumDao =
        appDatabase.albumDao()
}

@Module
interface AppDependencyBinder {

    @Binds
    fun bindApplication(application: AlbumApplication): Application

    @Binds
    fun bindContext(application: Application): Context

}
