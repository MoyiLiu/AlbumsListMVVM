package com.moyiliu.albumslistmvvm.di

import com.moyiliu.albumslistmvvm.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestAppDependencyModule::class,
        TestMainDependencyModule::class
    ]
)
interface TestAppInjector {
    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApplication): Builder

        fun build(): TestAppInjector
    }
}