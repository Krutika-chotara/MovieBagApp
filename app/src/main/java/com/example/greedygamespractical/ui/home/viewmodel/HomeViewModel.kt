package com.example.greedygamespractical.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.greedygamespractical.base.BaseViewModel
import com.example.greedygamespractical.constant.Constant
import com.example.greedygamespractical.data.remote.service.MovieService
import com.example.greedygamespractical.helper.EventWrapper
import com.example.greedygamespractical.helper.NetInfo
import com.example.greedygamespractical.ui.home.view.MovieListState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    val state = MutableLiveData<EventWrapper<MovieListState>>()
    fun state(): LiveData<EventWrapper<MovieListState>> = state

    val pageNumberLiveData = MutableLiveData<Int>(1)


    val movieService : MovieService by inject()

    fun fetchNowPlaying() = viewModelScope.launch {
        if(!NetInfo.isInternetOn()){
            state.postValue(EventWrapper(MovieListState.NoInternet))
            return@launch
        }

        runCatching {
            movieService.getMovieList(Constant.API_KEY,pageNumberLiveData.value!!)
        }.fold({
            state.postValue(EventWrapper(MovieListState.MovieListSuccess(it)))
        },{
            state.postValue(EventWrapper(MovieListState.MovieListFailure(it.message!!)))
        })
    }
}