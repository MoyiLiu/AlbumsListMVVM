package com.moyiliu.albumslistmvvm.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.setRefreshing(isRefreshing: Boolean?) {
    setRefreshing(isRefreshing ?: false)
}