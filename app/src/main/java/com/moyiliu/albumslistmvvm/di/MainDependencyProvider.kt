package com.moyiliu.albumslistmvvm.di

import androidx.lifecycle.ViewModel
import com.moyiliu.albumslistmvvm.MainActivity
import com.moyiliu.albumslistmvvm.di.annotation.ViewModelMapKey
import com.moyiliu.albumslistmvvm.ui.AlbumListFragment
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [MainDependencyBinder::class])
class MainDependencyProvider

@Module
interface MainDependencyBinder {

    @ContributesAndroidInjector
    fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun injectAlbumListFragment(): AlbumListFragment

    @Binds
    @IntoMap
    @ViewModelMapKey(AlbumViewModel::class)
    fun bindAlbumViewModel(viewModel: AlbumViewModel): ViewModel
}