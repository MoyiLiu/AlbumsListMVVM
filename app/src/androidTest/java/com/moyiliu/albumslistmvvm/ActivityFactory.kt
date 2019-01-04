package com.moyiliu.albumslistmvvm

import android.app.Activity
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.intercepting.SingleActivityFactory

inline fun <reified T : Activity> createSingleActivityFactoryFor(
    clazz: Class<T>, crossinline block: T.() -> Unit
) = object : SingleActivityFactory<T>(clazz) {
    override fun create(intent: Intent?): T =
        clazz.newInstance().apply { block() }
}

inline fun <reified T : Activity> singleActivityTestRule(
    initialTouchMode: Boolean = false,
    launchActivity: Boolean = true,
    crossinline block: T.() -> Unit
): ActivityTestRule<T> =
    ActivityTestRule(
        createSingleActivityFactoryFor(T::class.java, block),
        initialTouchMode, launchActivity
    )