package com.moyiliu.albumslistmvvm.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moyiliu.albumslistmvvm.AlbumApplication
import com.moyiliu.albumslistmvvm.TestApplication
import com.moyiliu.albumslistmvvm.db.AlbumDao
import com.moyiliu.albumslistmvvm.db.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        TestAppDependencyBinder::class,
        TestAlbumDependencyModule::class
    ]
)
class TestAppDependencyModule {
    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    fun providesTestApplicationDatabase(application: Application): AppDatabase =
        Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java).build()

    @Provides
    fun providesTestAlbumDao(appDatabase: AppDatabase): AlbumDao =
        appDatabase.albumDao()
}

@Module
interface TestAppDependencyBinder {
    @Binds
    fun bindApplication(application: TestApplication): Application

    @Binds
    fun bindContext(application: Application): Context
}