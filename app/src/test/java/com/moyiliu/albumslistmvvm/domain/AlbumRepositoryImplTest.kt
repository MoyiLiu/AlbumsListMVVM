package com.moyiliu.albumslistmvvm.domain

import com.moyiliu.albumslistmvvm.api.model.AlbumResponseModel
import com.moyiliu.albumslistmvvm.db.AlbumDao
import com.moyiliu.albumslistmvvm.domain.model.Album
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepositoryImpl
import com.moyiliu.albumslistmvvm.proxy.AlbumProxy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.lang.Exception

@RunWith(JUnit4::class)
class AlbumRepositoryImplTest {
    private val dao = mock(AlbumDao::class.java)
    private val proxy = mock(AlbumProxy::class.java)

    private val repo = AlbumRepositoryImpl(dao, proxy)

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun clear(){
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    @Throws(Exception::class)
    fun loadAlbums_insertLoadedAlbumsToDatabase() {
        `when`(proxy.fetchAlbums())
            .thenReturn(Single.just(listOf(ALBUM_1, ALBUM_2)))

        repo.loadAlbums()

        verify(dao).insertAlbums(ALBUM_1, ALBUM_2)
    }

    @Test
    @Throws(Exception::class)
    fun loadingState() {
        `when`(proxy.fetchAlbums())
            .thenReturn(Single.just(emptyList()))


        val testObserver = repo.observeLoading().test()

        repo.loadAlbums()

        testObserver
            .assertValueAt(0, false) //Initial value
            .assertValueAt(1, true) //Start loading
            .assertValueAt(2, false) //Finish loading
    }

    @Test
    @Throws(Exception::class)
    fun observeDatabaseAlbumTableUpdate_loadAlbums_overrideAlbums() {

        `when`(dao.observeAlbumsWithAscendingTitle())
            .thenReturn(Flowable.just(listOf(ALBUM_1, ALBUM_2)))

        repo.observeAlbums()
            .test()
            .assertValueAt(0, listOf(ALBUM_1, ALBUM_2))
    }

    private companion object {
        val ALBUM_1 = Album(userId = 1, id = 1, title = "album_1")
        val ALBUM_2 = Album(userId = 2, id = 2, title = "album_2")
    }
}