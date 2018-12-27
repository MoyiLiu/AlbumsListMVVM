package com.moyiliu.albumslistmvvm.db

import androidx.test.runner.AndroidJUnit4
import com.moyiliu.albumslistmvvm.domain.model.Album
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumEntityDatabaseTest : DatabaseTest() {

    private lateinit var albumDao: AlbumDao

    @Before
    fun setup() {
        albumDao = database.albumDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeReadAlbum() {
        albumDao.insertAlbums(*ALBUM_LIST.toTypedArray())
        albumDao
            .getAlbums()
            .test()
            .assertValue { result ->
                result.containsAll(ALBUM_LIST)
            }
    }

    @Test
    @Throws(Exception::class)
    fun writeReadTitleSortedAlbum() {
        albumDao.insertAlbums(*ALBUM_LIST.toTypedArray())
        albumDao
            .getAlbumsWithAscendingTitle()
            .test()
            .assertResult(TITLE_SORTED_ALBUM_LIST)
    }

    @Test
    @Throws(Exception::class)
    fun clearTable() {
        albumDao.insertAlbums(STUB_ALBUM_1)
        albumDao.getAlbums()
            .test()
            .assertValue { result -> result.isNotEmpty() }

        albumDao.deleteAll()
        albumDao.getAlbums()
            .test()
            .assertValue { result -> result.isEmpty() }
    }

    companion object {
        private val STUB_ALBUM_1 = Album(1, 1, "test_title_1")
        private val STUB_ALBUM_2 = Album(2, 2, "test_title_2")
        private val STUB_ALBUM_3 = Album(3, 3, "test_title_3")

        private val TITLE_SORTED_ALBUM_LIST = listOf(STUB_ALBUM_1, STUB_ALBUM_2, STUB_ALBUM_3)
        private val ALBUM_LIST = listOf(STUB_ALBUM_2, STUB_ALBUM_1, STUB_ALBUM_3)
    }

}