package com.moyiliu.albumslistmvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moyiliu.albumslistmvvm.domain.model.Album

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun albumDao(): AlbumDao
}