package com.example.greedygamespractical.ui.cast.view

import android.os.Bundle
import android.view.View
import com.example.greedygamespractical.R
import com.example.greedygamespractical.base.BaseFragment
import com.example.greedygamespractical.databinding.FragmentCastBinding
import com.example.greedygamespractical.ui.cast.viewmodel.CastViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CastFragment : BaseFragment<CastViewModel, FragmentCastBinding>(R.layout.fragment_cast) {
    override val viewModel: CastViewModel by viewModel()
    lateinit var castAdapter: CastAdapter
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

        viewModel.getMovieCredits(movieId)
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun initUi() {
        viewModel.movieCredits.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.cast?.isEmpty()!!) {
                    binding.txtNoData.visibility = View.VISIBLE
                } else {
                    castAdapter = CastAdapter(it.cast)
                    binding.recycleViewCast.adapter = castAdapter
                    binding.recycleViewCast.visibility = View.VISIBLE
                }
            }
        })

        viewModel.movieCreditError.observe(viewLifecycleOwner, {
            binding.txtNoData.visibility = View.VISIBLE
            binding.txtNoData.text = it
            binding.progressBar.visibility = View.GONE
        })
    }

    override fun setListeners() {
        binding.imgBack.setOnClickListener { activity?.onBackPressed() }
    }


}