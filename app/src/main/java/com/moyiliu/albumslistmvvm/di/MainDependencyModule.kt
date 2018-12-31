package com.moyiliu.albumslistmvvm.di

import com.moyiliu.albumslistmvvm.ui.AlbumActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainDependencyModule {
    @ContributesAndroidInjector
    fun injectAlbumActivity(): AlbumActivity
}