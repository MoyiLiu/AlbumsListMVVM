package com.moyiliu.albumslistmvvm.mock.proxy

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moyiliu.albumslistmvvm.FILE_NAME_ALBUM_RESPONSE_SUCCESSFUL
import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import com.moyiliu.albumslistmvvm.proxy.AlbumProxy
import com.moyiliu.albumslistmvvm.readMockApiResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestAlbumProxyImpl @Inject constructor(
    private val gson: Gson
) : AlbumProxy {

    override fun fetchAlbums(): Single<List<AlbumResponseModel>> =
        Single.just(FILE_NAME_ALBUM_RESPONSE_SUCCESSFUL)
            .map { fileName ->
                readMockApiResponse(fileName).let { responseBody ->
                    val type = object : TypeToken<List<AlbumResponseModel>>(){}.type
                    gson.fromJson<List<AlbumResponseModel>>(responseBody,type)
                }
            }.subscribeOn(Schedulers.io())
}