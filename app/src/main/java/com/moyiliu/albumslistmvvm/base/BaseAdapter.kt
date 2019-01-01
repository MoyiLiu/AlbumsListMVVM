package com.moyiliu.albumslistmvvm.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * An custom implementation of [RecyclerView.Adapter] is designed
 * to handle multiple types of view in one [RecyclerView], as long as
 * the data implement [BindingModel].
 *
 * For example:
 * ```
 * class AlbumBindingModel(val album: Album) : BaseAdapterBindingModel<AlbumItemLayoutBinding, Album>
 * class PictureBindingModel(val album: Picture) : BaseAdapterBindingModel<PictureItemLayoutBinding, Picture>
 *
 * ......
 *
 * val adapter = BaseAdapter()
 *
 * val list = listOf<BindingModel>(album_1, picture_1, album_2, picture_2)
 * adapter.dataList = list
 * ```
 */
class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var dataList: List<BindingModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder =
        dataList!![position]
            .createBinding(parent)
            .let { binding -> BaseViewHolder(binding) }

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        dataList?.get(position)?.apply {
            bindItem(holder.binding, position)
            itemClickListener = this@BaseAdapter.itemClickListener
        }
    }
}

class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

typealias OnItemClickListener = (rootView: View, item: BindingModel, position: Int) -> Unit
