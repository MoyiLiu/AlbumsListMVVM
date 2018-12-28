package com.moyiliu.albumslistmvvm.di

import com.moyiliu.albumslistmvvm.MainActivity
import com.moyiliu.albumslistmvvm.ui.AlbumFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainDependencyModule {

    @ContributesAndroidInjector
    fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun injectAlbumFragment(): AlbumFragment
}