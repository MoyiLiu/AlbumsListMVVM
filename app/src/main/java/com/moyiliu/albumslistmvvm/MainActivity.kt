package com.moyiliu.albumslistmvvm

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moyiliu.albumslistmvvm.base.BaseActivity
import com.moyiliu.albumslistmvvm.base.BaseAdapter
import com.moyiliu.albumslistmvvm.base.ViewModelFactory
import com.moyiliu.albumslistmvvm.base.viewModel
import com.moyiliu.albumslistmvvm.databinding.ActivityMainBinding
import com.moyiliu.albumslistmvvm.lifecycle.observe
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

//    override lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModel<AlbumViewModel>{ viewModelFactory }

    private val adapter = BaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView<ActivityMainBinding>(R.layout.activity_main){
            viewModel = this@MainActivity.viewModel
        }
        albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter

            layoutAnimation = AnimationUtils.loadLayoutAnimation(this@MainActivity, R.anim.layout_animation_from_bottom)
        }

        albumSwipeRefreshLayout.setOnRefreshListener(this)

        viewModel.albums.observe(this){
            adapter.dataList = it
        }
    }

    override fun onRefresh() {
        viewModel.refreshAlbums()
    }
}
