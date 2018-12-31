package com.moyiliu.albumslistmvvm

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.moyiliu.albumslistmvvm.di.DaggerTestAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplication: MultiDexApplication(), HasActivityInjector{

    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInject

    override fun onCreate() {
        super.onCreate()
        DaggerTestAppInjector.builder()
            .application(this)
            .build()
            .inject(this)
    }
}