package com.moyiliu.albumslistmvvm.db

import androidx.room.*
import com.moyiliu.albumslistmvvm.domain.model.Album
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Database bridging interface for table album ([Album])
 */
@Dao
interface AlbumDao {

    /** Attempts to get all [Album]s. */
    @Query("SELECT * FROM album")
    fun getAlbums(): Maybe<List<Album>>

    /** Attempts to observe album table modification. */
    @Query("SELECT * FROM album")
    fun observeAlbums(): Flowable<List<Album>>

    /** Attempts to get all [Album]s with ascending order of attribute title. */
    @Query("SELECT * FROM album ORDER BY title")
    fun getAlbumsWithAscendingTitle(): Maybe<List<Album>>

    /** Insert [Album] to Database. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(vararg albums: Album)

    /** Attempts to clear all [Album]s from the table. */
    @Query("DELETE FROM album")
    fun deleteAll()
}