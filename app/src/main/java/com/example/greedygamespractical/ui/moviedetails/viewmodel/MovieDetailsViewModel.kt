package com.example.greedygamespractical.ui.moviedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamespractical.base.BaseViewModel
import com.example.greedygamespractical.constant.Constant
import com.example.greedygamespractical.data.remote.MovieListResponse
import com.example.greedygamespractical.data.remote.service.MovieService
import com.example.greedygamespractical.helper.NetInfo
import com.example.greedygamespractical.model.MovieDetails
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class MovieDetailsViewModel : BaseViewModel() {
    private val _movieDetailsResponse = MutableLiveData<MovieDetails>(null)
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetailsResponse

    private val _movieDetailError = MutableLiveData<String>()
    val movieDetailsError: LiveData<String>
        get() = _movieDetailError

    //Similar Movie Live Data
    private val _similarMoviesResponse = MutableLiveData<MovieListResponse>()
    val similarMovies: LiveData<MovieListResponse>
        get() = _similarMoviesResponse

    //Similar Movie Error
    private val _similarMovieError = MutableLiveData<String>()
    val similarMovieError: LiveData<String>
        get() = _similarMovieError

    val movieService: MovieService by inject()
    fun getMovieDetails(movieId: Long) = viewModelScope.launch {
        if (!NetInfo.isInternetOn()) {
            return@launch
        }

        runCatching {
            movieService.getMovieDetails(movieId, Constant.API_KEY)
        }.fold({
            _movieDetailsResponse.postValue(it)
        }, {
            _movieDetailError.postValue(it.message)
        })
    }

    fun getSimilarMovies(movieId: Long) = viewModelScope.launch {
        if (!NetInfo.isInternetOn()) {
            return@launch
        }

        runCatching {
            movieService.getSimilarMovies(movieId, Constant.API_KEY)
        }.fold({
            _similarMoviesResponse.postValue(it)
        }, {
            _similarMovieError.postValue(it.message)
        })
    }
}