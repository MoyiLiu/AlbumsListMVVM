package com.moyiliu.albumslistmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moyiliu.albumslistmvvm.base.ProgressViewModel
import com.moyiliu.albumslistmvvm.base.ProgressViewModelDelegate
import com.moyiliu.albumslistmvvm.domain.model.Album
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepository
import com.moyiliu.albumslistmvvm.logging.error
import com.moyiliu.albumslistmvvm.rx.MultiDisposable
import com.moyiliu.albumslistmvvm.rx.MultiDisposableDelegate
import javax.inject.Inject

class AlbumViewModel @Inject constructor(
    private val repo: AlbumRepository
) : ViewModel(),
    ProgressViewModel by ProgressViewModelDelegate(),
    MultiDisposable by MultiDisposableDelegate() {

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>> get() = _albums

    init {

        repo.observeAlbums()
            .subscribe({ result ->
                _albums.postValue(result)
            }, {
                error(it) { "Failed to observe albums." }
            }).addToDisposables()

        repo.observeLoading()
            .subscribe({ loading ->
                if (loading) startProgress()
                else startProgress()
            }, {
                error(it) { "Failed to observe loading." }
            }).addToDisposables()

        repo.loadAlbums()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}