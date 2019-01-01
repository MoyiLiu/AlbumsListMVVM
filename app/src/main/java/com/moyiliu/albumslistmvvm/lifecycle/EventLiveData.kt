package com.moyiliu.albumslistmvvm.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class EventLiveData<E> : MutableLiveData<E>() {
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in E>) {
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a EventLiveData")
        }

        super.observe(owner, Observer { data ->
            if (data == null) return@Observer

            observer.onChanged(data)
            value = null
        })
    }
}