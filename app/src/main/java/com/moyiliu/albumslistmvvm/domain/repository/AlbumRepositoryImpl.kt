package com.moyiliu.albumslistmvvm.domain.repository

import android.annotation.SuppressLint
import com.moyiliu.albumslistmvvm.db.AlbumDao
import com.moyiliu.albumslistmvvm.domain.model.Album
import com.moyiliu.albumslistmvvm.proxy.AlbumProxy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumProxy: AlbumProxy
) : AlbumRepository {

    private val loadingSubject = BehaviorSubject.createDefault(false)

    /**
     * Ignoring disposable for fetching albums from remote resource,
     * due to the latest data should always be written to the database.
     * The presentation level (Album list in RecyclerView) would always
     * react on the data in the database.
     */
    override fun loadAlbums(): Completable =
        albumProxy.fetchAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { loadingSubject.onNext(true) }
            .doAfterTerminate { loadingSubject.onNext(false) }
            .flatMapCompletable { albums ->
                albumDao.insertAlbums(*albums.toTypedArray())
                Completable.complete()
            }

    override fun observeAlbums(): Flowable<List<Album>> =
        albumDao.observeAlbumsWithAscendingTitle()
            .subscribeOn(Schedulers.io())

    override fun observeLoading(): Observable<Boolean> =
        loadingSubject.subscribeOn(Schedulers.io())
}