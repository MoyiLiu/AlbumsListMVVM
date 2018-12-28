package com.moyiliu.albumslistmvvm.di

import androidx.lifecycle.ViewModel
import com.moyiliu.albumslistmvvm.di.annotation.ViewModelMapKey
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepository
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepositoryImpl
import com.moyiliu.albumslistmvvm.proxy.AlbumProxy
import com.moyiliu.albumslistmvvm.proxy.AlbumProxyImpl
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class AlbumDependencyModule{
    @Binds
    @Singleton
    abstract fun provideAlbumProxy(proxy: AlbumProxyImpl): AlbumProxy

    @Binds
    abstract fun provideAlbumRepo(repo: AlbumRepositoryImpl): AlbumRepository

    @Binds
    @IntoMap
    @ViewModelMapKey(AlbumViewModel::class)
    abstract fun bindAlbumViewModel(viewModel: AlbumViewModel): ViewModel
}