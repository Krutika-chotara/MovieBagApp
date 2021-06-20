package com.example.greedygamespractical.model


import com.google.gson.annotations.SerializedName

data class MovieReview(
    @SerializedName("author")
    var author: String? = "",
    @SerializedName("content")
    var content: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("author_details")
    var author_details: AuthorDetails,
    @SerializedName("created_at")
    var created_at: String? = "",
) {
    data class AuthorDetails(
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("username")
        var username: String? = "",
        @SerializedName("avatar_path")
        var avatar_path: String? = "",
        @SerializedName("rating")
        var rating: Int? = 0,
    )
}