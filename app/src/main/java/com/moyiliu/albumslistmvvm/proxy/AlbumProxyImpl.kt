package com.moyiliu.albumslistmvvm.proxy

import com.moyiliu.albumslistmvvm.api.AlbumApi
import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import com.moyiliu.albumslistmvvm.di.annotation.AlbumServer
import com.moyiliu.albumslistmvvm.domain.model.Album
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumProxyImpl @Inject constructor(
    @AlbumServer private val api: AlbumApi
) : AlbumProxy {
    override fun fetchAlbums(): Single<List<Album>> =
        api.getAllAlbums()
            .map { albumResponseModelList ->
                albumResponseModelList.map { responseItem ->
                    responseItem.toAlbum()
                }
            }
            .subscribeOn(Schedulers.io())

    private fun AlbumResponseModel.toAlbum(): Album = Album(id = id, userId = userId, title = title)

}