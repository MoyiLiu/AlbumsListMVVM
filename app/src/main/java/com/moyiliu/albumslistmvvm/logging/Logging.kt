package com.moyiliu.albumslistmvvm.logging

import android.util.Log
import com.moyiliu.albumslistmvvm.BuildConfig

inline fun Any.error(throwable: Throwable, block: () -> String) {
    Logger.DEFAULT.elog(throwable, block.invoke())
}

interface Logger {
    fun elog(throwable: Throwable, message: String)

    companion object {
        val DEFAULT = object : Logger {
            override fun elog(throwable: Throwable, message: String) {
                if (BuildConfig.DEBUG) {
                    Log.e("", message, throwable)
                }
            }
        }
    }
}