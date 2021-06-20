package com.example.greedygamespractical.ui.review.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamespractical.base.BaseViewModel
import com.example.greedygamespractical.constant.Constant
import com.example.greedygamespractical.data.remote.MovieReviewResponse
import com.example.greedygamespractical.data.remote.service.MovieService
import com.example.greedygamespractical.helper.NetInfo
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class ReviewViewModel : BaseViewModel(){
    //Movie Review (Synopsis) Live Data
    private val _movieReviewsResponse = MutableLiveData<MovieReviewResponse>(null)
    val movieReviews : LiveData<MovieReviewResponse>
        get() = _movieReviewsResponse

    //Movie Review (Synopsis) Error
    private val _movieReviewError = MutableLiveData<String>()
    val movieReviewError : LiveData<String>
        get() = _movieReviewError

    val movieService: MovieService by inject()

    fun getMovieReviews(movieId: Long) = viewModelScope.launch {
        if(!NetInfo.isInternetOn()){
            return@launch
        }

        runCatching {
            movieService.getMovieReviews(movieId, Constant.API_KEY)
        }.fold({
            _movieReviewsResponse.postValue(it)
        },{
            _movieReviewError.postValue(it.message)
        })
    }
}