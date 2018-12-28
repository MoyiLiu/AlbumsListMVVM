package com.moyiliu.albumslistmvvm.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

interface BaseAdapterBindingModel<in T, out E> :
    BindingModel where  E : Any, T : ViewDataBinding {
    val layout: Int
    override val dataModel: E

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun bindItem(binding: ViewDataBinding, position: Int) =
        bind(binding = binding as T, position = position)

    fun bind(binding: T, position: Int)

    override fun createBinding(parent: ViewGroup): ViewDataBinding =
        DataBindingUtil.inflate<T>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
}

interface BindingModel {
    val dataModel: Any?
    var itemClickListener: OnItemClickListener?
    fun createBinding(parent: ViewGroup): ViewDataBinding
    fun bindItem(binding: ViewDataBinding, position: Int)
}