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
interface AlbumDependencyModule{
    @Binds
    @Singleton
    fun bindAlbumProxy(proxy: AlbumProxyImpl): AlbumProxy

    @Binds
    fun bindAlbumRepo(repo: AlbumRepositoryImpl): AlbumRepository

    @Binds
    @IntoMap
    @ViewModelMapKey(AlbumViewModel::class)
    fun bindAlbumViewModel(viewModel: AlbumViewModel): ViewModel
}