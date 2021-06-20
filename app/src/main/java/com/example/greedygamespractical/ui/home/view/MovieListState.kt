package com.example.greedygamespractical.ui.home.view

import com.example.greedygamespractical.data.remote.MovieListResponse

sealed class MovieListState {
    object Initial : MovieListState()
    object NoInternet : MovieListState()
    object Loading : MovieListState()

    data class MovieListSuccess(var response : MovieListResponse) : MovieListState()
    data class MovieListFailure(var error : String) : MovieListState()
}