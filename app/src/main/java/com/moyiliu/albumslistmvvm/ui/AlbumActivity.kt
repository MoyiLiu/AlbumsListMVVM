package com.moyiliu.albumslistmvvm.ui

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moyiliu.albumslistmvvm.R
import com.moyiliu.albumslistmvvm.base.BaseActivity
import com.moyiliu.albumslistmvvm.base.BaseAdapter
import com.moyiliu.albumslistmvvm.base.ViewModelFactory
import com.moyiliu.albumslistmvvm.base.viewModel
import com.moyiliu.albumslistmvvm.databinding.AlbumActivityBinding
import com.moyiliu.albumslistmvvm.lifecycle.observe
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.album_activity.*
import javax.inject.Inject

class AlbumActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModel<AlbumViewModel> { viewModelFactory }

    private val adapter = BaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView<AlbumActivityBinding>(R.layout.album_activity) {
            viewModel = this@AlbumActivity.viewModel
        }

        albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AlbumActivity.adapter

            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
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