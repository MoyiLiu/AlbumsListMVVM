package com.moyiliu.albumslistmvvm.api

import com.moyiliu.albumslistmvvm.FILE_NAME_ALBUM_RESPONSE_SUCCESSFUL
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AlbumApiTest: AlbumServerTest() {


    private lateinit var api : AlbumApi

    @Before
    fun setup(){
        api = retrofit.create(AlbumApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun getNonEmptyAlbums(){
        enqueueResponse(FILE_NAME_ALBUM_RESPONSE_SUCCESSFUL)
        api.getAllAlbums()
            .test()
            .assertValue {it.isNotEmpty() }
            .assertValue { it.size == 8 }
    }

    @Test
    @Throws(Exception::class)
    fun fetchAlbums_checkFirstItemCorrect(){
        enqueueResponse(FILE_NAME_ALBUM_RESPONSE_SUCCESSFUL)
        api.getAllAlbums()
            .test()
            .assertValue {albums->
                albums[0].run {
                    id == 1 && userId ==1 && title == "quidem molestiae enim"
                }
            }
    }
}