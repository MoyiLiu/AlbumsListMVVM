package com.moyiliu.albumslistmvvm.proxy

import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import io.reactivex.Single

interface AlbumProxy{
    fun fetchAlbums(): Single<List<AlbumResponseModel>>
}