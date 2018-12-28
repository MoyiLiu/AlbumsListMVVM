package com.moyiliu.albumslistmvvm.base

import androidx.lifecycle.LiveData

interface ProgressViewModel {
    val progress: LiveData<Boolean>
    fun startProgress()
    fun stopProgress()
}