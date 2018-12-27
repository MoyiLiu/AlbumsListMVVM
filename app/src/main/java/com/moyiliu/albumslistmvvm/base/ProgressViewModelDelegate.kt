package com.moyiliu.albumslistmvvm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProgressViewModelDelegate : ProgressViewModel {
    private val _progress = MutableLiveData<Boolean>()
    override val progress: LiveData<Boolean> = _progress

    override fun startProgress() { _progress.postValue(true) }

    override fun endProgress() { _progress.postValue(false) }
}