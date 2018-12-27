package com.moyiliu.albumslistmvvm.api.model

import com.google.gson.annotations.Expose

/**
 * An API response model represents an Album
 */
data class AlbumResponseModel(
    @Expose val userId: Int,
    @Expose val id: Int,
    @Expose val title: String? = null
)