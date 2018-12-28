package com.moyiliu.albumslistmvvm

import android.os.Bundle
import androidx.navigation.NavController
import com.moyiliu.albumslistmvvm.base.BaseActivity
import com.moyiliu.albumslistmvvm.base.ViewModelFactory
import com.moyiliu.albumslistmvvm.base.viewModel
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

//    override lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModel<AlbumViewModel>{ viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
