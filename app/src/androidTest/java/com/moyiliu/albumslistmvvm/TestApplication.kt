package com.moyiliu.albumslistmvvm

import android.app.Application

/**
 * Use a separate Application for testing in order to
 * prevent initializing dependency injection.
 *
 * See [AlbumTestRunner]
 */
class TestApplication: Application()