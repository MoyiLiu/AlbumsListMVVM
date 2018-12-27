package com.moyiliu.albumslistmvvm.di

import android.app.Application
import android.content.Context
import com.moyiliu.albumslistmvvm.AlbumApplication
import com.moyiliu.albumslistmvvm.domain.model.Album
import dagger.Binds
import dagger.Module

/**
 * Dependency graph of the application
 */
@Module(
    includes = [
        AppDependencyBinder::class,
        DatabaseDependencyProvider::class,
        NetworkDependencyProvider::class,
        MainDependencyProvider::class
    ]
)
class AppDependencyProvider

@Module
interface AppDependencyBinder {
    @Binds
    fun bindApplication(application: Application): Application

    @Binds
    fun bindContext(application: Application): Context
}