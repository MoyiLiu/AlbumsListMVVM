package com.moyiliu.albumslistmvvm

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.moyiliu.albumslistmvvm.di.DaggerAppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AlbumApplication: MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector{

    @Inject
    lateinit var activityInject: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityInject

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppInjector.builder()
            .application(this)
            .build()
            .inject(this)
    }

}