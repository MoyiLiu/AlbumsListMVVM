package com.moyiliu.albumslistmvvm

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

fun waitForIdle() {
    UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).waitForIdle()
}