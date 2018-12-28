package com.moyiliu.albumslistmvvm.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

class MultiDisposableDelegate(
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MultiDisposable,
    Disposable by compositeDisposable,
    DisposableContainer by compositeDisposable