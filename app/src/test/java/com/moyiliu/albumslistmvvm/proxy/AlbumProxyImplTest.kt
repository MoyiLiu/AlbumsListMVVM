package com.moyiliu.albumslistmvvm.proxy

import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import com.moyiliu.albumslistmvvm.domain.model.Album
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class AlbumProxyImplTest {

    private val api = mock(AlbumApi::class.java)
    private val proxy = AlbumProxyImpl(api)

    @Before
    fun setup(){
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun clean(){
        RxJavaPlugins.reset()
    }
    @Test
    @Throws(Exception::class)
    fun getAlbums() {

        `when`(api.getAllAlbums())
            .thenReturn(Single.just(listOf(ALBUM_RES_1, ALBUM_RES_2)))

        proxy.fetchAlbums()
            .test()
            .assertValue { it.size == 2 }
            .assertValue {albums ->
                albums.containsAll(listOf(ALBUM_1, ALBUM_2))
            }
    }

    private companion object {
        val ALBUM_RES_1 = AlbumResponseModel(userId = 1, id = 1, title = "album_1")
        val ALBUM_RES_2 = AlbumResponseModel(userId = 2, id = 2, title = "album_2")

        val ALBUM_1 = Album(userId = 1, id = 1, title = "album_1")
        val ALBUM_2 = Album(userId = 2, id = 2, title = "album_2")
    }
}