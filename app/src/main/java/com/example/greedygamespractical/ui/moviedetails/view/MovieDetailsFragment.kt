package com.example.greedygamespractical.ui.moviedetails.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.greedygamespractical.R
import com.example.greedygamespractical.base.BaseFragment
import com.example.greedygamespractical.data.remote.MovieListResponse
import com.example.greedygamespractical.databinding.FragmentMovieDetailsBinding
import com.example.greedygamespractical.helper.NetInfo
import com.example.greedygamespractical.ui.moviedetails.viewmodel.MovieDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment :
    BaseFragment<MovieDetailsViewModel, FragmentMovieDetailsBinding>(R.layout.fragment_movie_details) {
    private var movieId: Long = 0
    override val viewModel: MovieDetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getLong("movie_id")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        setListeners()

        viewModel.getMovieDetails(movieId)
        viewModel.getSimilarMovies(movieId)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun initUi() {

        viewModel.movieDetails.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.llMovieDetails.visibility = View.VISIBLE
                binding.ratingBar.rating = (it.voteAverage?.toFloat())?.div(2) ?: 0.0f
                if (it.productionCompanies?.size!! > 0) {
                    val productionAdapter =
                        ProductionAdapter(it.productionCompanies)
                    binding.recycleViewProduction.visibility = View.VISIBLE
                    binding.txtHouse.visibility = View.VISIBLE
                    binding.recycleViewProduction.adapter = productionAdapter
                }
            }
        })


        viewModel.similarMovies.observe(viewLifecycleOwner, {
            initSimilarMovie(it)
        })

        viewModel.movieDetailsError.observe(viewLifecycleOwner, {
            binding.txtNoData.visibility = View.VISIBLE
            binding.txtNoData.text = it
            binding.progressBar.visibility = View.GONE
        })
    }

    override fun setListeners() {
        binding.imgBack.setOnClickListener { findNavController().navigateUp() }
        binding.btnReview.setOnClickListener { v ->
            if (NetInfo.isInternetOn()) {
                val bundle = bundleOf("movie_id" to movieId)
                findNavController().navigate(
                    R.id.action_movieDetailsFragment_to_reviewFragmetn,
                    bundle
                )
            } else {
                toast(getString(R.string.no_internet_message))
            }
        }
        binding.btnCast.setOnClickListener { v ->
            if (NetInfo.isInternetOn()) {
                val bundle = bundleOf("movie_id" to movieId)
                findNavController().navigate(
                    R.id.action_movieDetailsFragment_to_castFragment,
                    bundle
                )
            } else {
                toast(getString(R.string.no_internet_message))
            }
        }
    }

    /**
     * This method will initialize similar movies in movie details screen
     * @param movieListResponse: Movie List object received from server
     */
    private fun initSimilarMovie(movieListResponse: MovieListResponse?) {
        movieListResponse?.let { movieListData ->
            if (movieListData.results.isNotEmpty()) {
                binding.recycleViewSimilarMovies.visibility = View.VISIBLE
                binding.txtSimilar.visibility = View.VISIBLE
                val similarMoviesAdapter = SimilarMoviesAdapter(movieListData.results) {
                    if (NetInfo.isInternetOn()) {
                        val bundle = bundleOf("movie_id" to it.id)
                        findNavController().navigate(R.id.action_movieDetailsFragment_self, bundle)
                    } else {
                        toast(getString(R.string.no_internet_message))
                    }
                }
                binding.recycleViewSimilarMovies.adapter = similarMoviesAdapter

            }
        }
    }
}