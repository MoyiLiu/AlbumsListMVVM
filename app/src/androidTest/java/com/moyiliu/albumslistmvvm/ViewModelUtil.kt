package com.moyiliu.albumslistmvvm

import androidx.lifecycle.ViewModel
import com.moyiliu.albumslistmvvm.base.ViewModelFactory

object ViewModelUtil {
    fun <T : ViewModel> createFor(model: T): ViewModelFactory {
        return object : ViewModelFactory(mutableMapOf()) {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }
}