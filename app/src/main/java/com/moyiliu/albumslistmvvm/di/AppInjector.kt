package com.moyiliu.albumslistmvvm.di

import android.app.Application
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
        AndroidInjectionModule::class
    ]
)
interface AppInjector {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppInjector
    }
}