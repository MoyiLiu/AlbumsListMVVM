package com.moyiliu.albumslistmvvm.di

import com.moyiliu.albumslistmvvm.AlbumApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Dependency injector of the application
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppDependencyModule::class,
        MainDependencyModule::class
    ]
)
interface AppInjector {

    fun inject(application: AlbumApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: AlbumApplication): Builder

        fun build(): AppInjector
    }
}