package com.moyiliu.albumslistmvvm.rx

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

interface MultiDisposable: Disposable, DisposableContainer {
    fun Disposable.addToDisposables(): Boolean = add(this)
}