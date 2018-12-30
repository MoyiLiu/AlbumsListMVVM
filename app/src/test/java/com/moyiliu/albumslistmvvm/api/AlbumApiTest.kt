package com.moyiliu.albumslistmvvm.api

import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AlbumApiTest: AlbumServerTest() {

    private val responseFileName = "album-response.json"

    private lateinit var api : AlbumApi

    @Before
    fun setup(){
        api = retrofit.create(AlbumApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun getNonEmptyAlbums(){
        enqueueResponse(responseFileName)
        api.getAllAlbums()
            .test()
            .assertValue {it.isNotEmpty() }
            .assertValue { it.size == 8 }
    }

    @Test
    @Throws(Exception::class)
    fun fetchAlbums_checkFirstItemCorrect(){
        enqueueResponse(responseFileName)
        api.getAllAlbums()
            .test()
            .assertValue {albums->
                albums[0].run {
                    id == 1 && userId ==1 && title == "quidem molestiae enim"
                }
            }
    }
}