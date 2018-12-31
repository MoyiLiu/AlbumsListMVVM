package com.moyiliu.albumslistmvvm.proxy

import com.moyiliu.albumslistmvvm.domain.model.Album
import io.reactivex.Single

/**
 * A proxy for parsing network response date to a domain acceptable data
 */
interface AlbumProxy{

    /** Attempts to fetch albums from remote resource and parse to domain model. */
    fun fetchAlbums(): Single<List<Album>>
}