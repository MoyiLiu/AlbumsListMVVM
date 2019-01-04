package com.moyiliu.albumslistmvvm.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.moyiliu.albumslistmvvm.R
import com.moyiliu.albumslistmvvm.ViewModelUtil
import com.moyiliu.albumslistmvvm.domain.model.Album
import com.moyiliu.albumslistmvvm.domain.repository.AlbumRepository
import com.moyiliu.albumslistmvvm.matcher.withRecyclerView
import com.moyiliu.albumslistmvvm.singleActivityTestRule
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import com.moyiliu.albumslistmvvm.waitForIdle
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class AlbumActivityTest {

    private val testViewModel: AlbumViewModel by lazy {
        AlbumViewModel(repo)
    }

    private val repo = mock(AlbumRepository::class.java)

    @get:Rule
    val rule = singleActivityTestRule<AlbumActivity>(launchActivity = false) {
        viewModelFactory = ViewModelUtil.createFor(this@AlbumActivityTest.testViewModel)
    }

    @Test
    fun loadAlbum_matchFirstItem() {

        `when`(repo.loadAlbums())
            .thenReturn(Completable.complete())
        `when`(repo.observeAlbums())
            .thenReturn(Flowable.just(ALBUM_LIST))
        `when`(repo.observeLoading())
            .thenReturn(Observable.create { it.onNext(false) })

        rule.launchActivity(null)

        //wait for RecyclerView slide up animation
        waitForIdle()

        onView(withRecyclerView(R.id.albumRecyclerView).atPositionOnView(0, R.id.albumItemTitle))
            .check(matches(withText(TITLE_1)))
    }

    @Test
    fun loadAlbum_rotateScreen_matchFirstItem() {
        `when`(repo.loadAlbums())
            .thenReturn(Completable.complete())
        `when`(repo.observeAlbums())
            .thenReturn(Flowable.just(ALBUM_LIST))
        `when`(repo.observeLoading())
            .thenReturn(Observable.create { it.onNext(false) })

        rule.launchActivity(null)


        //wait for RecyclerView slide up animation
        waitForIdle()

        rotateScreen()

        //wait for screen rotation
        waitForIdle()

        onView(withRecyclerView(R.id.albumRecyclerView).atPositionOnView(0, R.id.albumItemTitle))
            .check(matches(withText(TITLE_1)))
    }

    @Test
    fun loadAlbum_failed_showSnackbar() {
        `when`(repo.loadAlbums())
            .thenReturn(Completable.error(Exception()))
        `when`(repo.observeAlbums())
            .thenReturn(Flowable.just(emptyList()))
        `when`(repo.observeLoading())
            .thenReturn(Observable.create { it.onNext(false) })

        rule.launchActivity(null)

        //wait for Snackbar slide up animation complete
        waitForIdle()

        //check error message is correctly displaying
        onView(withText(rule.activity.getString(R.string.album_fetch_error)))
            .check(matches(isDisplayed()))

        //dismiss the Snackbar
        onView(withText(rule.activity.getString(R.string.dismiss)))
            .perform(click())
    }

    private fun rotateScreen() {
        val context = InstrumentationRegistry.getInstrumentation().context
        val orientation = context.resources.configuration.orientation

        val activity = rule.activity
        activity.requestedOrientation =
                if (orientation == Configuration.ORIENTATION_PORTRAIT)
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private companion object {
        const val TITLE_1 = "album_1"
        const val TITLE_2 = "album_2"
        const val TITLE_3 = "album_3"
        val ALBUM_1 = Album(userId = 1, id = 1, title = TITLE_1)
        val ALBUM_2 = Album(userId = 2, id = 2, title = TITLE_2)
        val ALBUM_3 = Album(userId = 3, id = 3, title = TITLE_3)
        val ALBUM_LIST = listOf(ALBUM_1, ALBUM_2, ALBUM_3)
    }
}