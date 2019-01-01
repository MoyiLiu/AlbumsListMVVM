package com.moyiliu.albumslistmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moyiliu.albumslistmvvm.base.BindingModel
import com.moyiliu.albumslistmvvm.base.ProgressViewModel
import com.moyiliu.albumslistmvvm.base.ProgressViewModelDelegate
import com.moyiliu.albumslistmvvm.domain.model.AlbumBindingModel
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepository
import com.moyiliu.albumslistmvvm.lifecycle.EventLiveData
import com.moyiliu.albumslistmvvm.logging.error
import com.moyiliu.albumslistmvvm.rx.MultiDisposable
import com.moyiliu.albumslistmvvm.rx.MultiDisposableDelegate
import javax.inject.Inject

class AlbumViewModel @Inject constructor(
    private val repo: AlbumRepository
) : ViewModel(),
    ProgressViewModel by ProgressViewModelDelegate(),
    MultiDisposable by MultiDisposableDelegate() {

    private val _albums = MutableLiveData<List<AlbumBindingModel>>()
    private val _errorEvent = EventLiveData<AlbumErrorEvent>()

    /**
     * [AlbumBindingModel] could be replaced with [BindingModel]
     * only if multiple types of view are required
     * to be displayed in a single RecyclerView.
     * e.g.
     * ```
     * private val _album = MutableLiveData<List<BindingModel>>()
     * val albums: LiveData<List<BindingModel>> get() = _albums
     * ```
     */
    val albums: LiveData<List<AlbumBindingModel>> get() = _albums
    val errorEvent: LiveData<AlbumErrorEvent>
        get() = _errorEvent


    init {
        repo.observeLoading()
            .subscribe({ loading ->
                if (loading) startProgress()
                else stopProgress()
            }, {
                error(it) { "Failed to observe loading." }
            }).addToDisposables()

        /**
         * The View layer only reacts on the database.
         */
        repo.observeAlbums()
            .map { albums ->
                albums.map { album -> AlbumBindingModel(album) }
            }
            .subscribe({ result ->
                _albums.postValue(result)
            }, {
                error(it) { "Failed to observe albums." }
            }).addToDisposables()

        loadAlbums()
    }

    /**
     * Newly fetched data would be directly written to the database,
     * which won't be shown on the View layer immediately, but through
     * the database channel.
     */
    fun loadAlbums() {
        repo.loadAlbums()
            .subscribe({}, {
                error(it) { "Load albums failed." }
                _errorEvent.postValue(AlbumErrorEvent.ERROR)
            }).addToDisposables()
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }
}

enum class AlbumErrorEvent {
    ERROR
}