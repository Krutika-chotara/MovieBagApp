package com.example.greedygamespractical.di

import com.example.greedygamespractical.ui.cast.viewmodel.CastViewModel
import com.example.greedygamespractical.ui.home.viewmodel.HomeViewModel
import com.example.greedygamespractical.ui.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.greedygamespractical.ui.review.viewmodel.ReviewViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { HomeViewModel() }
    viewModel { MovieDetailsViewModel() }
    viewModel { CastViewModel() }
    viewModel { ReviewViewModel() }
}