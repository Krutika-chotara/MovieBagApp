package com.example.greedygamespractical.data.remote

import com.example.greedygamespractical.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("page") val page: Int,

    @field:SerializedName("total_results")
    val totalResults: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    val results: List<Movie>
)