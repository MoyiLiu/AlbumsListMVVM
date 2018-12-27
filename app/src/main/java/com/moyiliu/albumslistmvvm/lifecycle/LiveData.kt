package com.moyiliu.albumslistmvvm.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChange: (T?) -> Unit
): Observer<T> {
    val observer = Observer<T> { onChange(it) }
    observe(owner, observer)
    return observer
}