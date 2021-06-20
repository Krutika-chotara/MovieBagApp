package com.example.greedygamespractical.ui.review.view

import android.os.Bundle
import android.view.View
import com.example.greedygamespractical.R
import com.example.greedygamespractical.base.BaseFragment
import com.example.greedygamespractical.databinding.FragmentReviewBinding
import com.example.greedygamespractical.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.greedygamespractical.ui.review.viewmodel.ReviewViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewFragment :
    BaseFragment<ReviewViewModel, FragmentReviewBinding>(R.layout.fragment_review) {
    override val viewModel: ReviewViewModel by viewModel()
    private var movieId: Long = 0

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

        viewModel.getMovieReviews(movieId)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun initUi() {
        viewModel.movieReviews.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.results.isEmpty()) {
                    binding.txtNoData.visibility = View.VISIBLE
                } else {
                    binding.recycleReview.visibility = View.VISIBLE
                    binding.recycleReview.adapter = ReviewAdapter(it.results)
                }
            }
        })

        viewModel.movieReviewError.observe(viewLifecycleOwner, {
            binding.txtNoData.visibility = View.VISIBLE
            binding.txtNoData.text = it
            binding.progressBar.visibility = View.GONE
        })
    }

    override fun setListeners() {
        binding.imgBack.setOnClickListener { activity?.onBackPressed() }
    }

}