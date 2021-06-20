package com.example.greedygamespractical.ui.home.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greedygamespractical.R
import com.example.greedygamespractical.base.BaseFragment
import com.example.greedygamespractical.data.remote.MovieListResponse
import com.example.greedygamespractical.databinding.FragmentHomeBinding
import com.example.greedygamespractical.helper.NetInfo
import com.example.greedygamespractical.listener.OnLoadMoreListener
import com.example.greedygamespractical.model.Movie
import com.example.greedygamespractical.ui.home.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModel()
    lateinit var pagerAdapter: MoviesPagerAdapter
    private var moviewAdapter: MoviesAdapter? = null
    private var movieList = ArrayList<Movie?>()

    private var previousSize = 0
    private var totalRecords = 0
    private var mLoading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        viewModel.fetchNowPlaying()
    }

    override fun setViewModel() {
    }

    override fun initUi() {

        moviewAdapter = MoviesAdapter(
            movieList,
            binding.recycleViewMovies,
            binding.recycleViewMovies.layoutManager as LinearLayoutManager
        ) {
            if (NetInfo.isInternetOn()) {
                val bundle = bundleOf("movie_id" to it?.id)
                findNavController().navigate(
                    R.id.action_homeFragment_to_movieDetailsFragment,
                    bundle
                )
            } else {
                toast(getString(R.string.no_internet_message))
            }
        }
        binding.recycleViewMovies.adapter = moviewAdapter

        viewModel.state().observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            it.getContentIfNotHandled()?.let {
                renderState(it)
            }
        })

        moviewAdapter?.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
    }

    override fun setListeners() {
    }


    private fun renderState(state: MovieListState) {
        when (state) {
            MovieListState.Initial -> {

            }
            MovieListState.Loading -> {
                showProgressDialog()
            }
            is MovieListState.MovieListFailure -> {
                toast(state.error)
            }
            is MovieListState.MovieListSuccess -> {
                showMovies(state.response)
            }
            MovieListState.NoInternet -> {
                binding.progressBar.visibility = View.GONE
                binding.recycleViewMovies.visibility = View.GONE
                binding.appBar.visibility = View.GONE
                binding.txtNoData.visibility = View.VISIBLE
            }
        }
    }

    private fun showMovies(response: MovieListResponse) {
        mLoading = false

        if (movieList.size > 0 && movieList[movieList.size - 1] == null) {
            movieList.removeAt(movieList.size - 1)
            moviewAdapter?.notifyItemRemoved(movieList.size)
        }


        previousSize = movieList.size
        if (viewModel.pageNumberLiveData.value == 1) {
            movieList.clear()
        }

        movieList.addAll(response.results)
        if (viewModel.pageNumberLiveData.value == 1) {
            var pagerList = ArrayList<Movie?>()
            if (movieList.size > 5) {
                for (i in 0..4) {
                    pagerList.add(movieList[i])
                }
            }

            pagerAdapter = MoviesPagerAdapter(pagerList)
            binding.viewPagerMovies.adapter = pagerAdapter
            TabLayoutMediator(binding.tabLayout, binding.viewPagerMovies) { tab, position ->
            }.attach()

        }
        totalRecords = response.totalResults
        if (previousSize == 0) {
            moviewAdapter?.notifyDataSetChanged()
        } else {
            moviewAdapter?.notifyItemRangeInserted(previousSize, movieList.size)
        }

        moviewAdapter?.setLoaded()
    }

    private fun loadMoreData() {
        if (!mLoading) {
            movieList.add(null)
            binding.recycleViewMovies.post {
                moviewAdapter?.notifyItemInserted(movieList.size - 1)
            }
            mLoading = true
            viewModel.pageNumberLiveData.value =
                viewModel.pageNumberLiveData.value!!.plus(1)
            viewModel.fetchNowPlaying()
        } else {
            moviewAdapter?.setLoaded()
        }
    }
}