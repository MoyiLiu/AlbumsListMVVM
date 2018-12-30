package com.moyiliu.albumslistmvvm.proxy

import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class AlbumProxyImplTest {

    private val api = mock(AlbumApi::class.java)
    private val proxy = AlbumProxyImpl(api)

    @Test
    @Throws(Exception::class)
    fun getAlbums() {

        `when`(api.getAllAlbums())
            .thenReturn(Single.just(listOf(ALBUM_1, ALBUM_2)))

        proxy.fetchAlbums()
            .test()
            .assertValue { it.size == 2 }
            .assertValue {albums ->
                albums.containsAll(listOf(ALBUM_1, ALBUM_2))
            }
    }

    private companion object {
        val ALBUM_1 = AlbumResponseModel(userId = 1, id = 1, title = "album_1")
        val ALBUM_2 = AlbumResponseModel(userId = 2, id = 2, title = "album_2")
    }
}