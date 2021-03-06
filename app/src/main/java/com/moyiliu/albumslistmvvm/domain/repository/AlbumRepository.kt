package com.moyiliu.albumslistmvvm.domain.repository

import com.moyiliu.albumslistmvvm.domain.model.Album
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface AlbumRepository {
    fun loadAlbums(): Completable
    fun observeAlbums(): Flowable<List<Album>>
    fun observeLoading(): Observable<Boolean>
}