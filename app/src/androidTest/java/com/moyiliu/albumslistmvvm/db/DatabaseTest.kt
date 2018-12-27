package com.moyiliu.albumslistmvvm.db

import androidx.annotation.CallSuper
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DatabaseTest{
    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()
    internal lateinit var database: AppDatabase

    @Before
    fun initDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDatabase(){
        countingTaskExecutorRule.drainTasks(1, TimeUnit.SECONDS)
        database.close()
    }
}