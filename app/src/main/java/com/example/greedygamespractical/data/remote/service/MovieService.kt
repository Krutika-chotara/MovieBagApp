package com.example.greedygamespractical.data.remote.service

import com.example.greedygamespractical.data.remote.MovieListResponse
import com.example.greedygamespractical.data.remote.MovieReviewResponse
import com.example.greedygamespractical.model.MovieCredits
import com.example.greedygamespractical.model.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id : Long,
        @Query("api_key") apiKey: String
    ): MovieDetails


    /**
     * Get credits information
     * @param apiKey : API key for the movie db
     * @param id : Movie ID
     */
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id : Long,
        @Query("api_key") apiKey: String
    ) : MovieCredits

    /**
     * Get review information
     * @param apiKey : API key for the movie db
     * @param id : Movie ID
     */
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") id : Long,
        @Query("api_key") apiKey: String
    ) : MovieReviewResponse

    /**
     * Get similar movies
     * @param apiKey : API key for the movie db
     * @param id : Movie ID
     */
    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") id : Long,
        @Query("api_key") apiKey: String
    ) : MovieListResponse


}