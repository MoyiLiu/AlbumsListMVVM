package com.moyiliu.albumslistmvvm.api.model

import com.google.gson.annotations.Expose

data class AlbumResponseModel(
    @Expose val userId: Int,
    @Expose val id: Int,
    @Expose val title: String? = null
)