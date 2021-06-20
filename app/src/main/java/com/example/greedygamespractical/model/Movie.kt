package com.example.greedygamespractical.model


import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class Movie(
    @SerializedName("id")
    val id: Long,
    val title: String,

    @field:SerializedName("vote_average")
    val voteAverage: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    val overview: String
)