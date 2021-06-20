package com.example.greedygamespractical.ui.cast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamespractical.base.BaseViewModel
import com.example.greedygamespractical.constant.Constant
import com.example.greedygamespractical.data.remote.service.MovieService
import com.example.greedygamespractical.helper.NetInfo
import com.example.greedygamespractical.model.MovieCredits
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CastViewModel : BaseViewModel() {

    //Movie Credit (Synopsis) Live Data
    private val _movieCreditsResponse = MutableLiveData<MovieCredits>(null)
    val movieCredits: LiveData<MovieCredits>
        get() = _movieCreditsResponse

    //Movie Credit (Synopsis) Error
    private val _movieCreditError = MutableLiveData<String>()
    val movieCreditError: LiveData<String>
        get() = _movieCreditError

    val movieService: MovieService by inject()

    fun getMovieCredits(movieId: Long) = viewModelScope.launch {
        if (!NetInfo.isInternetOn()) {
            return@launch
        }

        runCatching {
            movieService.getMovieCredits(movieId, Constant.API_KEY)
        }.fold({
            _movieCreditsResponse.postValue(it)
        }, {
            _movieCreditError.postValue(it.message)
        })
    }
}