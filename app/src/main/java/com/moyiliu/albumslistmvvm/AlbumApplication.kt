package com.moyiliu.albumslistmvvm

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.moyiliu.albumslistmvvm.di.DaggerAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AlbumApplication : MultiDexApplication(), HasActivityInjector{

    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInject

    override fun onCreate() {
        super.onCreate()
        DaggerAppInjector.builder()
            .application(this)
            .build()
            .inject(this)
    }

}