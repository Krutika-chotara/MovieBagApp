package com.example.greedygamespractical.model


import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("adult")
    var adult: Boolean? = false,

    @SerializedName("backdrop_path")
    var backdropPath: String? = "",

    @SerializedName("budget")
    var budget: Long? = 0,

    @SerializedName("genres")
    var genres: List<Genre?>? = listOf(),

    @SerializedName("id")
    var id: Long? = 0,

    @SerializedName("imdb_id")
    var imdbId: String? = "",

    @SerializedName("original_language")
    var originalLanguage: String? = "",

    @SerializedName("original_title")
    var originalTitle: String? = "",

    @SerializedName("overview")
    var overview: String? = "",

    @SerializedName("popularity")
    var popularity: Double? = 0.0,


    @SerializedName("poster_path")
    var posterPath: String? = "",

    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany?>? = listOf(),

    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry?>? = listOf(),

    @SerializedName("release_date")
    var releaseDate: String? = "",

    @SerializedName("revenue")
    var revenue: Int? = 0,

    @SerializedName("runtime")
    var runtime: Int? = 0,

   
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage?>? = listOf(),

   
    @SerializedName("status")
    var status: String? = "",

   
    @SerializedName("tagline")
    var tagline: String? = "",

    @SerializedName("title")
    var title: String? = "",

   
    @SerializedName("video")
    var video: Boolean? = false,

   
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,

   
    @SerializedName("vote_count")
    var voteCount: Int? = 0,

   
    @SerializedName("status_code")
    var statusCode: Int? = 0,

   
    @SerializedName("status_message")
    var statusMessage: String?= ""

) {
    data class Genre(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class ProductionCompany(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("logo_path")
        var logoPath: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("origin_country")
        var originCountry: String? = ""
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        var iso31661: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )

    data class SpokenLanguage(
        @SerializedName("iso_639_1")
        var iso6391: String? = "",
        @SerializedName("name")
        var name: String? = ""
    )
}