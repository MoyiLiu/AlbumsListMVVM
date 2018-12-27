package com.moyiliu.albumslistmvvm.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KProperty

class ViewModelDelegate<T : ViewModel>(
    private val type: Class<T>,
    private val factory: () -> ViewModelProvider.Factory? = { null }
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        when (thisRef) {
            is FragmentActivity -> ViewModelProviders.of(thisRef, factory())[type]
            is Fragment -> ViewModelProviders.of(thisRef, factory())[type]
            else -> throw  IllegalArgumentException(
                """ViewModel can only be provided inside a `Fragment` or a `FragmentActivity`.
                        The property is defined inside `${thisRef?.javaClass?.name}`.
                    """.trimIndent()
            )
        }
}

inline fun <reified T : ViewModel> viewModel(
    noinline factory: () -> ViewModelProvider.Factory?
): ViewModelDelegate<T> =
    ViewModelDelegate(T::class.java, factory)