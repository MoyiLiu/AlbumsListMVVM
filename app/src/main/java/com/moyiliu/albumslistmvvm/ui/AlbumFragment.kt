package com.moyiliu.albumslistmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moyiliu.albumslistmvvm.R
import com.moyiliu.albumslistmvvm.base.BaseAdapter
import com.moyiliu.albumslistmvvm.base.BaseFragment
import com.moyiliu.albumslistmvvm.base.ViewModelFactory
import com.moyiliu.albumslistmvvm.base.viewModel
import com.moyiliu.albumslistmvvm.databinding.AlbumFragmentBinding
import com.moyiliu.albumslistmvvm.lifecycle.observe
import com.moyiliu.albumslistmvvm.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.album_fragment.*
import javax.inject.Inject

class AlbumFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener  {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by viewModel<AlbumViewModel> { viewModelFactory }

    private val adapter = BaseAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        bindContentView<AlbumFragmentBinding>(
            inflater, R.layout.album_fragment,container
        ){
            viewModel = this@AlbumFragment.viewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AlbumFragment.adapter

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