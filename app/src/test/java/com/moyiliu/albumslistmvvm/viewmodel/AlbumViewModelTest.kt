package com.moyiliu.albumslistmvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moyiliu.albumslistmvvm.argumentCaptor
import com.moyiliu.albumslistmvvm.domain.model.Album
import com.moyiliu.albumslistmvvm.domain.model.AlbumBindingModel
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepository
import com.moyiliu.albumslistmvvm.mock
import io.reactivex.Flowable
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class AlbumViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val repo = mock(AlbumRepository::class.java)

    @Before
    fun setup() {
        `when`(repo.observeAlbums())
            .thenReturn(Flowable.just(listOf(ALBUM_1, ALBUM_2)))

        `when`(repo.observeLoading())
            .thenReturn(Observable.create {
                it.onNext(false)
                it.onNext(true)
                it.onNext(false)
            })

    }

    @Test
    @Throws(Exception::class)
    fun initialisation() {
        AlbumViewModel(repo)
        val inOrder = inOrder(repo)

        inOrder.apply {
            verify(repo).observeLoading()
            verify(repo).observeAlbums()
            verify(repo).loadAlbums()
        }
    }

    @Test
    @Throws(Exception::class)
    fun pushAlbumLiveData() {
        val observer = mock<Observer<List<AlbumBindingModel>>>()

        val captor = argumentCaptor<List<AlbumBindingModel>>()

        val viewModel = AlbumViewModel(repo)

        viewModel.albums.observeForever(observer)

        verify(observer).onChanged(captor.capture())

        assertEquals(2, captor.value.size)
        assertEquals(captor.value[0].dataModel, ALBUM_1)
        assertEquals(captor.value[1].dataModel, ALBUM_2)

    }

    private companion object {
        val ALBUM_1 = Album(userId = 1, id = 1, title = "album_1")
        val ALBUM_2 = Album(userId = 2, id = 2, title = "album_2")
    }
}