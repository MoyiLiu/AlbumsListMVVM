package com.moyiliu.albumslistmvvm.di

import android.content.Context
import androidx.room.Room
import com.moyiliu.albumslistmvvm.db.AlbumDao
import com.moyiliu.albumslistmvvm.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dependency graph for Database
 */
@Module
class DatabaseDependencyProvider {

    @Singleton
    @Provides
    fun providesApplicationDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "album_app_database").build()

    @Provides
    fun providesAlbumDao(appDatabase: AppDatabase): AlbumDao =
        appDatabase.albumDao()
}