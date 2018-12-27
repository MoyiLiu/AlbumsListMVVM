package com.moyiliu.albumslistmvvm.api

import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * RESTFUL API for Album
 */
interface AlbumApi {

    /** Attempt to fetch Albums from remote resource. */
    @GET("albums")
    fun getAllAlbums(): Single<List<AlbumResponseModel>>
}