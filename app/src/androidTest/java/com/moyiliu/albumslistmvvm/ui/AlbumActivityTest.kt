package com.moyiliu.albumslistmvvm.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.moyiliu.albumslistmvvm.R
import com.moyiliu.albumslistmvvm.matcher.withRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AlbumActivityTest {
    @get:Rule
    val rule = ActivityTestRule<AlbumActivity>(AlbumActivity::class.java)

    @Test
    fun loadAlbum_matchFirstItem() {
        onView(withRecyclerView(R.id.albumRecyclerView).atPositionOnView(0, R.id.albumItemTitle))
            .check(matches(withText(EXPECTED_FIRST_ITEM_TITLE)))
    }

    @Test
    fun loadAlbum_rotateScreen_matchFirstItem() {

        rotateScreen()

        onView(withRecyclerView(R.id.albumRecyclerView).atPositionOnView(0, R.id.albumItemTitle))
            .check(matches(withText(EXPECTED_FIRST_ITEM_TITLE)))
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
        const val EXPECTED_FIRST_ITEM_TITLE = "eaque aut omnis a"
    }
}