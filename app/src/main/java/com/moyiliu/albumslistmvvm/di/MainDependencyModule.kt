package com.moyiliu.albumslistmvvm.di

import com.moyiliu.albumslistmvvm.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainDependencyModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

//    @ContributesAndroidInjector
//    fun injectAlbumListFragment(): AlbumListFragment
}