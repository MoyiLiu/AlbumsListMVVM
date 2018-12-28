package com.moyiliu.albumslistmvvm.domain.model

import com.moyiliu.albumslistmvvm.R
import com.moyiliu.albumslistmvvm.base.BaseAdapterBindingModel
import com.moyiliu.albumslistmvvm.base.OnItemClickListener
import com.moyiliu.albumslistmvvm.databinding.AlbumItemLayoutBinding

class AlbumBindingModel(
    override val dataModel: Album
) : BaseAdapterBindingModel<AlbumItemLayoutBinding, Album> {

    override val layout: Int
        get() = R.layout.album_item_layout
    override var itemClickListener: OnItemClickListener? = null

    override fun bind(binding: AlbumItemLayoutBinding, position: Int) {
        binding.apply {
            album = dataModel
        }
    }
}